package org.treading.threads.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarManagerTest {


    @Test
    public void carSelectingAndRegisteringTwoThreads() throws InterruptedException {

        final CarManager carManager = new CarManager();

        final Thread selectCarsThread = new Thread(() -> {
            try {
                carManager.selectAllCars();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread registerAllCars = new Thread(() -> {
            try {
                carManager.registerAllCars();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        selectCarsThread.start();
        registerAllCars.start();

        selectCarsThread.join();
        registerAllCars.join();

        assertFalse(selectCarsThread.isAlive());
        assertFalse(registerAllCars.isAlive());

        System.out.println(carManager);

        assertTrue(carManager.getCars().size() == 4);

    }
}
