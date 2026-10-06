package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class EgovRateLimiterSecurityPortTest {
    @Test
    void sixthAttemptIsDeniedUntilWindowExpires() {
        String key=UUID.randomUUID().toString();
        long now=System.currentTimeMillis();
        for(int i=0;i<5;i++) assertTrue(EgovRateLimiter.allow(key,5,600000,now));
        assertFalse(EgovRateLimiter.allow(key,5,600000,now+599999));
        assertTrue(EgovRateLimiter.allow(key,5,600000,now+600000));
    }

    @Test
    void concurrentAttemptsCannotExceedLimit() throws Exception {
        String key=UUID.randomUUID().toString();
        ExecutorService pool=Executors.newFixedThreadPool(8);
        AtomicInteger allowed=new AtomicInteger();
        try {
            java.util.List<Callable<Void>> calls=new java.util.ArrayList<>();
            for(int i=0;i<40;i++) calls.add(() -> {if(EgovRateLimiter.allow(key,5,600000)) allowed.incrementAndGet();return null;});
            for(Future<Void> result:pool.invokeAll(calls)) result.get();
            assertEquals(5,allowed.get());
        } finally { pool.shutdownNow(); }
    }
}
