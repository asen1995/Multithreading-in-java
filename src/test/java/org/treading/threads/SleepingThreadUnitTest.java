package org.treading.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SleepingThreadUnitTest {


    @Test
    public void sleepingThreadJoiningAndWaitForTwoThreadsToFinish() throws InterruptedException {

        final Thread firstThread = new SleepingThread("ThreadA", 10_000);
        final Thread secondThread = new SleepingThread("ThreadB", 12_000);

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        //waiting for both threads to be over to proceed with the main thread from his test and assert that both threads have finished
        assertFalse(firstThread.isAlive());
        assertFalse(secondThread.isAlive());
    }
}
