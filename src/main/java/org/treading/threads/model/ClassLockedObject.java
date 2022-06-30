package org.treading.threads.model;

public class ClassLockedObject {

    private static String staticContent;

    public void classLock1() throws InterruptedException {

        synchronized (LockedObject.class) {
            System.out.println("classLock1 ready for print but has to wait");
            System.out.println("classLock1 resumed");
            System.out.println(ClassLockedObject.staticContent);
        }
    }

    public void classLock2() throws InterruptedException {

        synchronized (LockedObject.class) {
            System.out.println("classLock2 ready for print but has to wait");
            Thread.sleep(5000);
            ClassLockedObject.staticContent = "This is a static content generated in 5 seconds";
            System.out.println("classLock2 resumed and will notify");

        }
    }
}
