package org.treading.threads;

import org.junit.jupiter.api.Test;
import org.treading.threads.model.LockedObject;

import static org.junit.jupiter.api.Assertions.*;

class LockedThreadTests {

    @Test
    public void lockAndNotifyAllTest() throws InterruptedException {

        final LockedObject data = new LockedObject();

        final Thread numberStringBuilderThread = new Thread(() -> {
            try {
                data.generateStringContent();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread dataPrinterThread = new Thread(() -> {
            try {
                data.lock2Printing();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread addingNumbersThread = new Thread(() -> {
            try {
                data.lock3AddingNumbers(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        dataPrinterThread.start();
        addingNumbersThread.start();
        numberStringBuilderThread.start();

        numberStringBuilderThread.join();
        dataPrinterThread.join();
        addingNumbersThread.join();

        assertFalse(dataPrinterThread.isAlive());
        assertFalse(addingNumbersThread.isAlive());
        assertFalse(numberStringBuilderThread.isAlive());

        assertNotNull(data.getContent());
    }
}
