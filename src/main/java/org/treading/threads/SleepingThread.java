package org.treading.threads;

public class SleepingThread extends Thread {

    private final int millis;

    public SleepingThread(String name, int millis) {
        super(name);
        this.millis = millis;
    }

    @Override
    public void run() {
        System.out.println(String.format("Thread %s will sleep %d seconds", getName(), ( this.millis / 1000 )));
        try {
            Thread.sleep(this.millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Thread %s finished", getName()));

    }
}
