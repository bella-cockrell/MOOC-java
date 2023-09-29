package com.project.app;

import com.project.app.HelloRunnable;

public class App 
{
    public static void main( String[] args )
    {
        (new Thread(new HelloRunnable())).start(); 
    }
}
