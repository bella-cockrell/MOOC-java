package com.project.app;

import com.project.app.HelloRunnable;
import com.project.app.HelloThread;
import com.project.app.SleepMessages.*;

public class App 
{
    public static void main( String[] args )
    {
        (new Thread(new HelloRunnable())).start(); 

        (new HelloThread()).start();        
    }
}