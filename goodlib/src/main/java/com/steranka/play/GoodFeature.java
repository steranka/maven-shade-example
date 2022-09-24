package com.steranka.play;

public class GoodFeature {
    public String sayGoodbye(String name){
        LogIt logit = new LogIt();
        String result = logit.sayHello(name);
        String newResult = result.replace("Hello", "Goodbye");
        System.out.println(newResult);
        return newResult;
    }
}
