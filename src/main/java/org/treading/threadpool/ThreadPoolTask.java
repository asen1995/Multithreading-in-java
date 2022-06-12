package org.treading.threadpool;

public class ThreadPoolTask extends Thread{

    private final Integer sleepingTime;

    public ThreadPoolTask(String name, Integer sleepingTime) {
        super(name);
        this.sleepingTime = sleepingTime;
    }

    @Override
    public void run() {

        System.out.println(String.format("thread %s started and will last %d seconds" , getName() , (sleepingTime / 1000)));
        try {
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("thread %s finished" ,getName()));
    }
}
