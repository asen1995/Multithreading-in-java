package org.treading.database;

import java.util.Arrays;
import java.util.List;

public class Database {

    public List<String> selectAllMan() throws InterruptedException {
        final String sql = "some sql";
        System.out.println("selectAllMan takes 3 seconds");
        Thread.sleep(3000);
        System.out.println("selectAllMan finished");

        return Arrays.asList("manFirst","manSecond","manThird");
    }
    public List<String> selectAllWomen() throws InterruptedException {
        final String sql = "some sql";
        System.out.println("selectAllWomen takes 3 seconds");
        Thread.sleep(3000);

        System.out.println("selectAllWomen finished");
        return Arrays.asList("woman First","Woman fifth"," Woman six");
    }

}
