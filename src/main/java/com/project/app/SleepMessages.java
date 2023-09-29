package com.project.app;

public class SleepMessages{
    public static void printMessages() throws InterruptedException {
        System.out.println("Hello from inside SleepMessages");
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            Thread.sleep(500);
            System.out.println(importantInfo[i]);
        }
    }
}
