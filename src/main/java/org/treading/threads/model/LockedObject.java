package org.treading.threads.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LockedObject {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void generateStringContent() throws InterruptedException {

        Thread.sleep(5000);

        synchronized (this) {

            System.out.println("generation started");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                builder.append(String.format("%d,", i));
            }
            setContent(builder.toString());

            System.out.println("generation finished and invoking notify after 2 seconds");
            Thread.sleep(2000);

            System.out.println("notifying all other threads that are locked in the monitor");

            notifyAll();
        }
    }

    public void lock2Printing() throws InterruptedException {

        synchronized (this) {
            System.out.println("lock2Printing ready for print but has to wait");
            wait();
            System.out.println("lock2Printing resumed");
            System.out.println(this.getContent());
        }
    }

    public void lock3AddingNumbers(Integer number) throws InterruptedException {

        synchronized (this) {

            System.out.println("lock3Printing ready for print");
            wait();
            System.out.println("lock3Printing resumed");
            final List<Integer> numbersPlus100 = Arrays.stream(this.getContent().split(","))
                    .map(i -> Integer.parseInt(i))
                    .map(i -> i + number)
                    .collect(Collectors.toList());
            System.out.println("lock3Printing splitted array : " + numbersPlus100);
        }
    }
}
