package com.steranka.play;

public class LogIt {
    public String sayHello(String name, String greeting){
        String theGreeting = greeting + " " + name;
        System.out.println(theGreeting);
        return theGreeting;
    }
}
