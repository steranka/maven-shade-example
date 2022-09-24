# Create a Java Maven Hello World program

This directory contains a hello world program that is compiled using Maven.

It was created using Maven as well; using this command:

```shell
mvn archetype:generate -DgroupId=com.steranka.play -DartifactId=helloworld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

See also https://facingissuesonit.com/2017/06/06/how-to-create-maven-java-console-project/ for more details.

# Building this app and running it
To build the application simply run maven as shown
```
mvn clean install
```

At the time I created this I was using maven v3.8.4 as shown next

```
mvn --version
Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: D:\p\apache-maven-3.8.4
Java version: 1.8.0_271, vendor: Oracle Corporation, runtime: D:\p\jdk1.8.0_271\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

# To run the application
To run the application just use java -jar as follows:
```
java -jar target\helloworld-1.0.0.jar
```
If you get an exception (the one shown below), that is normal as that is
the reason this repo was created.
```
Hello World!
What's up, Sam
Exception in thread "main" java.lang.NoSuchMethodError: com.steranka.play.LogIt.sayHello(Ljava/lang/String;)Ljava/lang/String;
        at com.steranka.play.GoodFeature.sayGoodbye(GoodFeature.java:6)
        at com.steranka.play.HelloWorldApp.main(HelloWorldApp.java:15)
```

See [../doc/01-Starting.md](../doc/01-Starting.md) and follow along.
