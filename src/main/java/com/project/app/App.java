package com.project.app;

import com.project.app.HelloRunnable;
import com.project.app.HelloThread;
import com.project.app.SimpleThreads.MessageLoop;
import static com.project.app.SimpleThreads.threadMessage;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {       

        long patience = 1 * 60 * 60; //patience of 3600 milliseconds

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException ex) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Finally!");

    }
}