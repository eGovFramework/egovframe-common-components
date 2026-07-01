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
package egovframework.com.ext.msg.server.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import egovframework.com.ext.msg.server.ChatServerEndPoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Extension;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import jakarta.websocket.server.HandshakeRequest;

/**
 * {@link ChatServerAppConfig}의 무상태 endpoint 생성과 방별 session 격리를 검증한다.
 */
class ChatServerAppConfigConcurrencyTest {

	private static final int SESSIONS_PER_ROOM = 8;

	@SuppressWarnings("unchecked")
	private static Map<String, Set<Session>> chatrooms() throws Exception {
		Field field = ChatServerEndPoint.class.getDeclaredField("CHATROOMS");
		field.setAccessible(true);
		return (Map<String, Set<Session>>) field.get(null);
	}

	@AfterEach
	void clearChatrooms() throws Exception {
		try {
			chatrooms().clear();
		} catch (NoSuchFieldException e) {
			// Test A는 CHATROOMS가 없던 기존 configurator 구현에서도 실행할 수 있어야 한다.
		}
	}

	@Test
	void getEndpointInstance_crossThread_returnsDistinctInstances() throws Exception {
		ChatServerAppConfig config = new ChatServerAppConfig();
		AtomicReference<ChatServerEndPoint> endpointA = new AtomicReference<>();
		AtomicReference<ChatServerEndPoint> endpointB = new AtomicReference<>();
		AtomicReference<Throwable> failure = new AtomicReference<>();
		CountDownLatch roomAModified = new CountDownLatch(1);
		CountDownLatch roomBModified = new CountDownLatch(1);
		CountDownLatch endpointACreated = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(4);

		Thread requestA = new Thread(() -> {
			try {
				config.modifyHandshake(null, new FakeHandshakeRequest(URI.create("/chat/roomA")), null);
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				roomAModified.countDown();
				done.countDown();
			}
		}, "chat-request-roomA");

		Thread requestB = new Thread(() -> {
			try {
				roomAModified.await();
				config.modifyHandshake(null, new FakeHandshakeRequest(URI.create("/chat/roomB")), null);
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				roomBModified.countDown();
				done.countDown();
			}
		}, "chat-request-roomB");

		Thread upgradeA = new Thread(() -> {
			try {
				roomBModified.await();
				endpointA.set(config.getEndpointInstance(ChatServerEndPoint.class));
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				endpointACreated.countDown();
				done.countDown();
			}
		}, "chat-upgrade-roomA");

		Thread upgradeB = new Thread(() -> {
			try {
				endpointACreated.await();
				endpointB.set(config.getEndpointInstance(ChatServerEndPoint.class));
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				done.countDown();
			}
		}, "chat-upgrade-roomB");

		requestA.start();
		requestB.start();
		upgradeA.start();
		upgradeB.start();
		done.await();

		assertNull(failure.get(), "다른 upgrade 스레드에서 endpoint 생성 중 예외가 발생하면 안 된다: " + failure.get());
		assertNotSame(endpointA.get(), endpointB.get(), "각 WebSocket 연결은 서로 다른 endpoint 인스턴스를 사용해야 한다");
	}

	@Test
	void handleOpen_concurrentRooms_keepsSessionsIsolated() throws Exception {
		ChatServerAppConfig config = new ChatServerAppConfig();
		List<Session> roomASessions = new ArrayList<>();
		List<Session> roomBSessions = new ArrayList<>();
		List<Thread> workers = new ArrayList<>();
		AtomicReference<Throwable> failure = new AtomicReference<>();
		CountDownLatch ready = new CountDownLatch(SESSIONS_PER_ROOM * 2);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(SESSIONS_PER_ROOM * 2);

		for (int i = 0; i < SESSIONS_PER_ROOM; i++) {
			Session roomASession = new FakeSession();
			Session roomBSession = new FakeSession();
			roomASessions.add(roomASession);
			roomBSessions.add(roomBSession);
			workers.add(openWorker(config, roomASession, "roomA", ready, start, done, failure));
			workers.add(openWorker(config, roomBSession, "roomB", ready, start, done, failure));
		}

		for (Thread worker : workers) {
			worker.start();
		}
		ready.await();
		start.countDown();
		done.await();

		assertNull(failure.get(), "동시 방 입장 중 예외가 발생하면 안 된다: " + failure.get());
		Set<Session> roomAUsers = chatrooms().get("roomA");
		Set<Session> roomBUsers = chatrooms().get("roomB");
		assertNotNull(roomAUsers);
		assertNotNull(roomBUsers);
		assertEquals(new HashSet<>(roomASessions), roomAUsers);
		assertEquals(new HashSet<>(roomBSessions), roomBUsers);
		assertNotSame(roomAUsers, roomBUsers, "서로 다른 방은 session set을 공유하면 안 된다");
		assertTrue(Collections.disjoint(roomAUsers, roomBUsers), "방 간 session 교차오염이 없어야 한다");
	}

	private static Thread openWorker(ChatServerAppConfig config, Session session, String room,
			CountDownLatch ready, CountDownLatch start, CountDownLatch done,
			AtomicReference<Throwable> failure) {
		return new Thread(() -> {
			ready.countDown();
			try {
				start.await();
				ChatServerEndPoint endpoint = config.getEndpointInstance(ChatServerEndPoint.class);
				endpoint.handleOpen(session, room);
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				done.countDown();
			}
		}, "chat-open-" + room);
	}

	private static final class FakeHandshakeRequest implements HandshakeRequest {

		private final URI requestUri;

		FakeHandshakeRequest(URI requestUri) {
			this.requestUri = requestUri;
		}

		@Override
		public URI getRequestURI() {
			return requestUri;
		}

		@Override
		public Map<String, List<String>> getHeaders() {
			return Collections.emptyMap();
		}

		@Override
		public Principal getUserPrincipal() {
			return null;
		}

		@Override
		public boolean isUserInRole(String role) {
			return false;
		}

		@Override
		public Object getHttpSession() {
			return null;
		}

		@Override
		public Map<String, List<String>> getParameterMap() {
			return Collections.emptyMap();
		}

		@Override
		public String getQueryString() {
			return null;
		}
	}

	private static final class FakeSession implements Session {

		private final Map<String, Object> userProperties = new HashMap<>();
		private final RemoteEndpoint.Basic basicRemote = new NoOpBasicRemote();

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

	private static final class NoOpBasicRemote implements RemoteEndpoint.Basic {

		@Override
		public void sendText(String text) throws IOException {
			// no-op
		}

		@Override
		public void sendText(String partialMessage, boolean isLast) throws IOException {
			// no-op
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
