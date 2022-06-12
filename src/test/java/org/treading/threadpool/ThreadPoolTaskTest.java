package org.treading.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

class ThreadPoolTaskTest {

    @Test
    public void fixedThreadPoolWithTwoThreadsTest() throws InterruptedException {

        final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        final Thread databaseSelect = new ThreadPoolTask("Database selection" , 10000);
        final Thread serviceCalling = new ThreadPoolTask("service calling" , 6000);

        final Future<?> databaseSubmit = threadPool.submit(databaseSelect);
        final Future<?> serviceCallingSubmit = threadPool.submit(serviceCalling);

        while (!(databaseSubmit.isDone() && serviceCallingSubmit.isDone())){
            System.out.println("threads still in process...");
            Thread.sleep(3000);
        }
        System.out.println("both threads finished");

        assertTrue(threadPool.getCompletedTaskCount() == 2);
        assertTrue(threadPool.getActiveCount() == 0);

        threadPool.shutdown();

        assertTrue(threadPool.isShutdown());
    }


    @Test
    public void cashedThreadPoolWith50ThreadsTest() throws InterruptedException {

        final ThreadPoolExecutor cachedThreadPool =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();

        final int threadsCount = 50;

        for (int i = 0; i < threadsCount; i++) {
            final Thread thread = new ThreadPoolTask(String.format("ThreadNumber %d  ", i), 10000);
            cachedThreadPool.submit(thread);
        }

        while (cachedThreadPool.getActiveCount() != 0) {
            System.out.println("threads still in process...");
            Thread.sleep(5000);
        }

        //reuse the 50 threads from the cached thread pool
        for (int i = 0; i < threadsCount; i++) {
            final Thread thread = new ThreadPoolTask(String.format("ThreadNumber %d  ", i), 10000);
            cachedThreadPool.submit(thread);
        }

        while (cachedThreadPool.getActiveCount() != 0) {
            System.out.println("second time cached threads still in process...");
            Thread.sleep(5000);
        }

        //we are expecting the pool to still have 50 threads
        assertEquals(50, cachedThreadPool.getPoolSize());
        assertEquals(0, cachedThreadPool.getActiveCount());
    }
}
