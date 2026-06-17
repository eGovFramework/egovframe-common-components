/*
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.ext.msg.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import jakarta.websocket.CloseReason;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Extension;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

/**
 * {@link UsersServerEndPoint}의 connectedAllUsers(synchronizedSet) 순회 동기화 검증.
 *
 * <p>
 * synchronizedSet은 개별 연산만 동기화하고 순회는 사용자가 set에 직접 synchronized 해야 한다.
 * 순회 중 다른 스레드가 add/remove로 set을 변이하면 HashSet의 fail-fast 이터레이터가
 * ConcurrentModificationException을 던진다. 수정 전 코드(맨몸 순회)에서는 이 테스트가
 * 예외를 잡아내고, 순회를 synchronized 블록으로 감싼 수정 후에는 무예외로 통과한다.
 * </p>
 *
 * <p>
 * 실제 WebSocket 연결 없이 {@link Session} 인터페이스를 인라인 구현한 fake로 구동한다.
 * reader는 리플렉션으로 private getUsers()를 반복 호출(순수 순회, I/O 0)하고,
 * mutator는 static connectedAllUsers 필드에 fake Session을 add/remove 한다.
 * </p>
 */
class UsersServerEndPointConcurrencyTest {

	private static final int THREADS = 16;
	private static final int ROUNDS = 300;

	/**
	 * static connectedAllUsers 필드를 리플렉션으로 얻는다.
	 */
	@SuppressWarnings("unchecked")
	private static Set<Session> connectedAllUsers() throws Exception {
		Field field = UsersServerEndPoint.class.getDeclaredField("connectedAllUsers");
		field.setAccessible(true);
		return (Set<Session>) field.get(null);
	}

	@AfterEach
	void clearConnectedUsers() throws Exception {
		connectedAllUsers().clear();
	}

	@Test
	void getUsers_undersimultaneous_addRemove_doesNotThrow() throws Exception {
		final Set<Session> users = connectedAllUsers();

		// 순회 시작 시점에 비어있지 않도록 baseline 세션을 채워둔다.
		for (int i = 0; i < 8; i++) {
			users.add(new FakeSession("baseline-" + i));
		}

		final Method getUsers = UsersServerEndPoint.class.getDeclaredMethod("getUsers");
		getUsers.setAccessible(true);

		final UsersServerEndPoint endpoint = new UsersServerEndPoint();
		final AtomicReference<Throwable> failure = new AtomicReference<>();
		final CountDownLatch ready = new CountDownLatch(THREADS);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(THREADS);

		for (int t = 0; t < THREADS; t++) {
			final boolean reader = (t % 2 == 0);
			final int id = t;
			new Thread(() -> {
				ready.countDown();
				try {
					start.await();
					for (int r = 0; r < ROUNDS; r++) {
						if (reader) {
							// 순수 순회 경로: private getUsers() 반복 호출
							getUsers.invoke(endpoint);
						} else {
							// 구조 변경: add 후 remove 반복
							Session s = new FakeSession("mutator-" + id + "-" + r);
							users.add(s);
							users.remove(s);
						}
					}
				} catch (Throwable e) {
					// 리플렉션 호출 예외는 원인으로 펼쳐 기록한다.
					failure.compareAndSet(null, e.getCause() != null ? e.getCause() : e);
				} finally {
					done.countDown();
				}
			}).start();
		}

		ready.await();
		start.countDown();
		done.await();

		assertNull(failure.get(), "순회 중 구조 변경으로 예외가 발생하면 안 된다: " + failure.get());
	}

	@Test
	void broadcast_모든세션에전달하고_실패세션이있어도나머지는전달된다() throws Exception {
		final Set<Session> users = connectedAllUsers();
		FakeSession s1 = new FakeSession("u1");
		FakeSession bad = new FakeSession("bad", true); // sendText에서 IOException
		FakeSession s2 = new FakeSession("u2");
		users.add(s1);
		users.add(bad);
		users.add(s2);

		Method broadcast = UsersServerEndPoint.class.getDeclaredMethod("broadcast", String.class);
		broadcast.setAccessible(true);
		broadcast.invoke(new UsersServerEndPoint(), "hello");

		// 실패 세션이 있어도 정상 세션은 모두 수신한다(전송 실패가 나머지를 막지 않음)
		assertEquals(List.of("hello"), ((RecordingBasicRemote) s1.getBasicRemote()).sentText());
		assertEquals(List.of("hello"), ((RecordingBasicRemote) s2.getBasicRemote()).sentText());
		assertTrue(((RecordingBasicRemote) bad.getBasicRemote()).sendAttempted(),
			"실패 세션도 전송이 시도되어야 한다");
	}

	/**
	 * {@link Session} 인터페이스의 인라인 fake 구현.
	 *
	 * <p>
	 * getUserProperties()만 사전 채운 Map을 반환하고, 나머지 추상 메서드는 모두
	 * 기본값(null/0/false)을 반환한다. 망/실제 WebSocket 연결은 필요하지 않다.
	 * </p>
	 */
	private static final class FakeSession implements Session {

		private final Map<String, Object> userProperties = new HashMap<>();
		private final RecordingBasicRemote basicRemote;

		FakeSession(String username) {
			this(username, false);
		}

		FakeSession(String username, boolean failOnSend) {
			userProperties.put("username", username);
			this.basicRemote = new RecordingBasicRemote(failOnSend);
		}

		@Override
		public Map<String, Object> getUserProperties() {
			return userProperties;
		}

		@Override
		public RemoteEndpoint.Basic getBasicRemote() {
			return basicRemote;
		}

		@Override
		public WebSocketContainer getContainer() {
			return null;
		}

		@Override
		public void addMessageHandler(MessageHandler handler) {
			// no-op
		}

		@Override
		public <T> void addMessageHandler(Class<T> clazz, MessageHandler.Whole<T> handler) {
			// no-op
		}

		@Override
		public <T> void addMessageHandler(Class<T> clazz, MessageHandler.Partial<T> handler) {
			// no-op
		}

		@Override
		public Set<MessageHandler> getMessageHandlers() {
			return null;
		}

		@Override
		public void removeMessageHandler(MessageHandler handler) {
			// no-op
		}

		@Override
		public String getProtocolVersion() {
			return null;
		}

		@Override
		public String getNegotiatedSubprotocol() {
			return null;
		}

		@Override
		public List<Extension> getNegotiatedExtensions() {
			return null;
		}

		@Override
		public boolean isSecure() {
			return false;
		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public long getMaxIdleTimeout() {
			return 0;
		}

		@Override
		public void setMaxIdleTimeout(long milliseconds) {
			// no-op
		}

		@Override
		public void setMaxBinaryMessageBufferSize(int length) {
			// no-op
		}

		@Override
		public int getMaxBinaryMessageBufferSize() {
			return 0;
		}

		@Override
		public void setMaxTextMessageBufferSize(int length) {
			// no-op
		}

		@Override
		public int getMaxTextMessageBufferSize() {
			return 0;
		}

		@Override
		public RemoteEndpoint.Async getAsyncRemote() {
			return null;
		}

		@Override
		public String getId() {
			return null;
		}

		@Override
		public void close() throws IOException {
			// no-op
		}

		@Override
		public void close(CloseReason closeReason) throws IOException {
			// no-op
		}

		@Override
		public URI getRequestURI() {
			return null;
		}

		@Override
		public Map<String, List<String>> getRequestParameterMap() {
			return null;
		}

		@Override
		public String getQueryString() {
			return null;
		}

		@Override
		public Map<String, String> getPathParameters() {
			return null;
		}

		@Override
		public Principal getUserPrincipal() {
			return null;
		}

		@Override
		public Set<Session> getOpenSessions() {
			return null;
		}
	}

	/**
	 * {@link RemoteEndpoint.Basic}의 인라인 fake. sendText(String) 호출만 기록하며,
	 * failOnSend가 true면 IOException을 던져 전송 실패 세션을 시뮬레이션한다.
	 */
	private static final class RecordingBasicRemote implements RemoteEndpoint.Basic {

		private final boolean failOnSend;
		private final List<String> sent = new ArrayList<>();
		private boolean sendAttempted;

		RecordingBasicRemote(boolean failOnSend) {
			this.failOnSend = failOnSend;
		}

		List<String> sentText() {
			return sent;
		}

		boolean sendAttempted() {
			return sendAttempted;
		}

		@Override
		public void sendText(String text) throws IOException {
			sendAttempted = true;
			if (failOnSend) {
				throw new IOException("simulated send failure");
			}
			sent.add(text);
		}

		@Override
		public void sendText(String partialMessage, boolean isLast) throws IOException {
			sendText(partialMessage);
		}

		@Override
		public void sendBinary(ByteBuffer data) throws IOException {
			// no-op
		}

		@Override
		public void sendBinary(ByteBuffer partialByte, boolean isLast) throws IOException {
			// no-op
		}

		@Override
		public void sendObject(Object data) throws IOException, EncodeException {
			// no-op
		}

		@Override
		public OutputStream getSendStream() throws IOException {
			return null;
		}

		@Override
		public Writer getSendWriter() throws IOException {
			return null;
		}

		@Override
		public void setBatchingAllowed(boolean allowed) throws IOException {
			// no-op
		}

		@Override
		public boolean getBatchingAllowed() {
			return false;
		}

		@Override
		public void flushBatch() throws IOException {
			// no-op
		}

		@Override
		public void sendPing(ByteBuffer applicationData) throws IOException {
			// no-op
		}

		@Override
		public void sendPong(ByteBuffer applicationData) throws IOException {
			// no-op
		}
	}
}
