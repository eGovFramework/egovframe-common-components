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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import egovframework.com.ext.msg.server.config.ChatServerAppConfig;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;

/**
 * 세션 집합을 순회하는 경로가 동시 접속·해제 중에도 안전한지 검증한다.
 *
 * <p>{@code Collections.synchronizedSet}은 개별 연산만 보호하고 순회는 호출자가 감싸야 한다.
 * 감싸지 않으면 순회 중 다른 스레드가 집합을 바꿀 때 {@code ConcurrentModificationException}이
 * 발생한다.</p>
 */
class ChatServerEndPointConcurrencyTest {

    private static final int THREADS = 8;
    private static final int ROUNDS = 200;

    /** 필요한 메서드만 응답하는 Session 대역. */
    private Session session(String username) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);

        InvocationHandler handler = (proxy, method, args) -> {
            switch (method.getName()) {
                case "getUserProperties":
                    return properties;
                case "getBasicRemote":
                    return null;
                case "equals":
                    return proxy == args[0];
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "toString":
                    return "session-" + username;
                default:
                    return null;
            }
        };
        return (Session) Proxy.newProxyInstance(Session.class.getClassLoader(),
                new Class<?>[] { Session.class }, handler);
    }

    /** 핸드셰이크가 검증한 사용자명을 담은 EndpointConfig 대역. */
    private EndpointConfig config(String username) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ChatServerAppConfig.AUTHENTICATED_USERNAME_PROPERTY, username);

        InvocationHandler handler = (proxy, method, args) ->
                "getUserProperties".equals(method.getName()) ? properties : null;
        return (EndpointConfig) Proxy.newProxyInstance(EndpointConfig.class.getClassLoader(),
                new Class<?>[] { EndpointConfig.class }, handler);
    }

    @SuppressWarnings("unchecked")
    private Set<Session> chatroomUsers(ChatServerEndPoint endPoint) throws Exception {
        Field field = ChatServerEndPoint.class.getDeclaredField("chatroomUsers");
        field.setAccessible(true);
        return (Set<Session>) field.get(endPoint);
    }

    private Set<String> callGetUsers(ChatServerEndPoint endPoint) throws Exception {
        Method method = ChatServerEndPoint.class.getDeclaredMethod("getUsers");
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<String> users = (Set<String>) method.invoke(endPoint);
        return users;
    }

    @Test
    @DisplayName("핸드셰이크에서 검증한 사용자명이 세션에 바인딩된다")
    void handleOpenBindsAuthenticatedUsername() throws Exception {
        ChatServerEndPoint endPoint = new ChatServerEndPoint();
        Session userSession = session(null);

        endPoint.handleOpen(userSession, config("hong"), "room1");

        assertEquals("hong", userSession.getUserProperties().get("username"));
        assertEquals("room1", userSession.getUserProperties().get("room"));
        assertEquals(1, chatroomUsers(endPoint).size());
    }

    @Test
    @DisplayName("사용자 목록 순회 중 접속·해제가 일어나도 예외가 발생하지 않는다")
    void getUsersIsSafeWhileSessionsChange() throws Exception {
        ChatServerEndPoint endPoint = new ChatServerEndPoint();
        Set<Session> sessions = chatroomUsers(endPoint);
        for (int i = 0; i < 50; i++) {
            sessions.add(session("user" + i));
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREADS);
        AtomicInteger failures = new AtomicInteger();

        for (int t = 0; t < THREADS; t++) {
            final boolean mutating = t % 2 == 0;
            pool.execute(() -> {
                try {
                    start.await();
                    for (int i = 0; i < ROUNDS; i++) {
                        if (mutating) {
                            Session added = session("temp" + i);
                            sessions.add(added);
                            sessions.remove(added);
                        } else {
                            callGetUsers(endPoint);
                        }
                    }
                } catch (Exception e) {
                    failures.incrementAndGet();
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        assertTrue(done.await(60, TimeUnit.SECONDS), "테스트 스레드가 제한 시간 안에 끝나야 한다");
        pool.shutdownNow();

        assertEquals(0, failures.get(), "순회 중 발생한 예외 횟수");
    }
}
