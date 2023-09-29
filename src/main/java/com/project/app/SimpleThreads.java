package com.project.app;

public class SimpleThreads {
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n", ///%n is newline
                          threadName,
                          message);
    }

    private static class MessageLoop
        implements Runnable {
        public void run() {
            String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            try {
                for (int i = 0;
                     i < importantInfo.length;
                     i++) {
                    Thread.sleep(4000); //thread will take 4 secs to execute
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void execute() {
        long patience = 1000 * 60 * 60; //patience of 1 hour

        // if (args.length > 0) {
        //     try {
        //         patience = Long.parseLong(args[0]) * 1000;
        //     } catch (NumberFormatException ex) {
        //         System.err.println("Argument must be an integer.");
        //         System.exit(1);
        //     }
        // }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            try {
                t.join(1000); //attempts to join every second so we should see 4 prints per thread
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        threadMessage("Finally!");

    }

}

