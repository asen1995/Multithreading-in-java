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
}
