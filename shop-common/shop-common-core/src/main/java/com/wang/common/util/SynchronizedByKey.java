package com.wang.common.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SynchronizedByKey {

    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap<>();

    public void exec(String key, Runnable statement) {
        ReentrantLock mutex4Key = null;
        ReentrantLock mutexInCache;

        do {
            if (mutex4Key != null) {
                mutex4Key.unlock();
            }

            mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());
            mutex4Key.lock();
            mutexInCache = mutexCache.get(key);
        } while (mutexInCache == null || mutex4Key != mutexInCache);

        synchronized(mutex4Key){
            try {
                statement.run();
            } finally {
//                if (mutex4Key.getQueueLength() == 0) {
                    mutexCache.remove(key);
//                }
                mutex4Key.unlock();
            }
        }
    }
}
