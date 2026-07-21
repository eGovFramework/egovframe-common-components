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

import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import egovframework.com.ext.msg.server.model.ChatMessage;
import jakarta.websocket.CloseReason;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Extension;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

/**
 * {@link ChatServerEndPoint}가 방 세션 집합(Collections.synchronizedSet)을 순회하는 모든 경로에서
 * 동시 멤버십 변경 중 ConcurrentModificationException 없이 동작하는지 검증한다.
 * (브로드캐스트·handleClose·getUsers 순회가 synchronized 블록 밖에 있으면 예외가 발생했다)
 */
class ChatServerEndPointConcurrencyTest {

	private static final int SEED_SESSIONS = 8;

	@Test
	void broadcast_concurrentWithMembershipChange_noConcurrentModification() throws Exception {
		final String room = "room";
		ChatServerEndPoint endpoint = new ChatServerEndPoint();

		Session sender = new FakeSession();
		sender.getUserProperties().put("username", "sender");
		endpoint.handleOpen(sender, room);
		for (int i = 0; i < SEED_SESSIONS; i++) {
			Session seed = new FakeSession();
			seed.getUserProperties().put("username", "seed" + i);
			endpoint.handleOpen(seed, room);
		}

		AtomicReference<Throwable> failure = new AtomicReference<>();
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(2);
		final int iterations = 500;

		// 순회 스레드: 브로드캐스트(else 분기)가 방 세션 집합을 반복 순회한다.
		Thread iterator = new Thread(() -> {
			try {
				start.await();
				ChatMessage message = new ChatMessage();
				message.setMessage("hello");
				for (int i = 0; i < iterations && failure.get() == null; i++) {
					endpoint.handleMessage(message, sender, room);
				}
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				done.countDown();
			}
		}, "chat-broadcast");

		// 변경 스레드: 동일 방에 세션을 계속 add/remove 하여 구조적 변경을 유발한다.
		Thread mutator = new Thread(() -> {
			try {
				start.await();
				for (int i = 0; i < iterations && failure.get() == null; i++) {
					Session churn = new FakeSession();
					churn.getUserProperties().put("username", "churn" + i);
					endpoint.handleOpen(churn, room);
					endpoint.handleClose(churn, room);
				}
			} catch (Throwable e) {
				failure.compareAndSet(null, e);
			} finally {
				done.countDown();
			}
		}, "chat-mutator");

		iterator.start();
		mutator.start();
		start.countDown();
		done.await();

		// 동기화되지 않은 순회는 ConcurrentModificationException을 던진다.
		assertNull(failure.get(), "동시 멤버십 변경 중 순회에서 예외가 발생하면 안 된다: " + failure.get());
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
