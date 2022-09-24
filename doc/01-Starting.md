# Getting Started

The numbered documentation pages walk you through learning about the maven-shade-plugin
using this code base as an example.

Start by getting the code on GitHub at https://github.com/steranka/maven-shade-example
using the `git clone` command as shown next:

```
git clone git@github.com:steranka/maven-shade-example.git
```

Build the code and run it to observe the problem
```
cd maven-shade-example
mvn clean install
java -jar helloworld\target\helloworld-1.0.0.jar
```
*NOTE: The above commands work in both UNIX and Windows.*

When you run this if you are in the master branch, then you'll get an error as shown below
```
Hello World!
What's up, Sam
Exception in thread "main" java.lang.NoSuchMethodError: com.steranka.play.LogIt.sayHello(Ljava/lang/String;)Ljava/lang/String;
        at com.steranka.play.GoodFeature.sayGoodbye(GoodFeature.java:6)
        at com.steranka.play.HelloWorldApp.main(HelloWorldApp.java:15)
```

Next, you'll fix this error.  [Click here](02-Fixing-Problem.md)
