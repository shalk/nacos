package com.alibaba.nacos.client.lock.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NLockFactoryTest {

    @Test
    void getLockWithoutExpireTime() {
        NLock lock = NLockFactory.getLock("testKey");
        assertNotNull(lock);
        assertEquals("testKey", lock.getKey());
        assertEquals(-1L, lock.getExpiredTime());
    }

    @Test
    void getLockWithExpireTime() {
        NLock lock = NLockFactory.getLock("testKey", 1000L);
        assertNotNull(lock);
        assertEquals("testKey", lock.getKey());
        assertEquals(1000L, lock.getExpiredTime());
    }

    @Test
    void getLockWithNullKey() {
        NLock lock = NLockFactory.getLock(null);
        assertNotNull(lock);
        assertEquals(null, lock.getKey());
        assertEquals(-1L, lock.getExpiredTime());
    }

    @Test
    void getLockWithEmptyKey() {
        NLock lock = NLockFactory.getLock("");
        assertNotNull(lock);
        assertEquals("", lock.getKey());
        assertEquals(-1L, lock.getExpiredTime());
    }

    @Test
    void getLockWithNullKeyAndExpireTime() {
        NLock lock = NLockFactory.getLock(null, 1000L);
        assertNotNull(lock);
        assertEquals(null, lock.getKey());
        assertEquals(1000L, lock.getExpiredTime());
    }

    @Test
    void getLockWithEmptyKeyAndExpireTime() {
        NLock lock = NLockFactory.getLock("", 1000L);
        assertNotNull(lock);
        assertEquals("", lock.getKey());
        assertEquals(1000L, lock.getExpiredTime());
    }
}