package org.treading.threads.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class CarManager {

    private List<String> cars;

    public void selectAllCars() throws InterruptedException {
        System.out.println("selectAllCars will take 5 seconds");

        synchronized (this) {
            Thread.sleep(5_000);
            this.cars = Arrays.asList("Mazda", "Audi", "BMW", "Renault");
            System.out.println("selectAllCars finished");
            notifyAll();
        }
    }

    public void registerAllCars() throws InterruptedException {

        synchronized (this) {

            System.out.println("registerAllCars will wait till we get the cars");
            final int maxTimeout = 30_000;
            wait(maxTimeout);
            System.out.println("registerAllCars resumed");

            this.cars = this.cars.stream().map(car -> car = car + " M4012VR ").collect(Collectors.toList());

            System.out.println("registerAllCars finished");
        }

    }
}
