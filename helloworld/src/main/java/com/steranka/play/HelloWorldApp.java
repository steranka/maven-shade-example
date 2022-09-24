package com.steranka.play;

/**
 * Hello world!
 *
 */
public class HelloWorldApp
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        LogIt logIt = new LogIt();
        logIt.sayHello("Sam", "What's up,");
        GoodFeature goodFeature = new GoodFeature();
        goodFeature.sayGoodbye("Sam");

    }
}
