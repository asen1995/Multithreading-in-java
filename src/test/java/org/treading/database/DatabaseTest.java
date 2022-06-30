package org.treading.database;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void databaseSelectManAndWomenConcurrently() throws InterruptedException {

        final Database database = new Database();

        final AtomicReference<List<String>> mans = new AtomicReference<>();
        Thread manSelector = new Thread(() -> {
            try {
                mans.set(database.selectAllMan());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final AtomicReference<List<String>> women = new AtomicReference<>();
        Thread womanSelector = new Thread(() -> {
            try {
                women.set(database.selectAllWomen());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        manSelector.start();
        womanSelector.start();

        manSelector.join();
        womanSelector.join();

        assertFalse(manSelector.isAlive());
        assertFalse(womanSelector.isAlive());

        final List<String> combinedList = Stream.of(mans.get(), women.get())
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        System.out.println(combinedList);

        assertTrue(combinedList.size() == 6);

    }

}
