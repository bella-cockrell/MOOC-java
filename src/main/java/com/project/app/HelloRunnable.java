package com.project.app;

import com.project.app.SleepMessages;

public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a thread inside HelloRunnable");
        try {
            new SleepMessages().printMessages();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
