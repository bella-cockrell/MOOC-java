package com.project.app;

import com.project.app.HelloRunnable;
import com.project.app.HelloThread;
import com.project.app.SimpleThreads;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {   
        (new Thread(new HelloRunnable())).start();   
        new HelloThread().start();
        SimpleThreads.execute();
    }
}