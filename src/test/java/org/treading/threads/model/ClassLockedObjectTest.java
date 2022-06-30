package org.treading.threads.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClassLockedObjectTest {


    @Test
    public void classLock() throws InterruptedException {

        final ClassLockedObject data = new ClassLockedObject();

        final Thread classLock1 = new Thread(() -> {
            try {
                data.classLock1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread classLock2 = new Thread(() -> {
            try {
                data.classLock2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        classLock1.start();
        classLock2.start();

        classLock1.join();
        classLock2.join();

        assertFalse(classLock1.isAlive());
        assertFalse(classLock2.isAlive());

    }

}
