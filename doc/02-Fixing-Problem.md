# Fixing the problem using the maven shade plugin
To continue with this example, the fix is on a git feature branch, so to 
get the "fixed code", you need to run:

```
git checkout feature/fixing-problem
```

This version works.  

Below is a description of two things I tried to get this to work.
The 2nd attempt worked and runs without an exception.

# A Failed attempt (1st attempt)
I found out how to rename classes, see https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html.

And configured maven-shade-plugin as follows:
```
                <relocation>
                  <pattern>com.steranka.play</pattern>
                  <shadedPattern>com.shaded.steranka.play</shadedPattern>
                  <excludes>
                    <exclude>com.steranka.play.GoodFeature</exclude>
                  </excludes>
                </relocation>
```
I then compiled and ran it.
### Run it
When I ran the program I got the same exception I saw earlier.
```
java -jar helloworld\target\helloworld-1.0.0.jar
```
So what happened, why didn't it work?

### So what did I do wrong?  What happened?
I configured the maven-shade-plugin to rename 
```
com.steranka.play.LogIt
```
to
```
com.shaded.steranka.play.LogIt
```

I wanted somehow to make this change only for the artifact com.steranka.play:loglib:1.0.0 
but I couldn't find out how to do that.  So this approach didn't work.

When I examined the jar file I found this
```
jar tf target\helloworld-1.0.0.jar
META-INF/MANIFEST.MF
META-INF/
com/steranka/play/HelloWorldApp.class
META-INF/maven/com.steranka.play/helloworld/
META-INF/maven/com.steranka.play/helloworld/pom.xml
META-INF/maven/com.steranka.play/helloworld/pom.properties
com/steranka/play/GoodFeature.class
META-INF/maven/com.steranka.play/goodlib/pom.properties
com/shaded/steranka/play/LogIt.class
META-INF/maven/com.steranka.play/loglib/pom.xml
META-INF/maven/com.steranka.play/loglib/pom.properties
```

So the reason this didn't work is that it renamed *every* `com.steranka.play.LogIt` class that
it saw to the shaded name, and I only wanted it to shade the version 1.0.0 of the loglib jar.
I haven't found a way to do shading on a specific jar file.  That's a task for another day.

To continue with this example, I knew that I needed to shade just the Goodlib jar file.

# 2nd Attempt - Shading the goodlib.jar file
The `goodlib.jar` file **needs** to use version 1.0 so that is the jar that needs
to be changed to shade the desired `LogIt` class.

This will allow the consumers of this jar file (namely helloworld.jar) to
use this JAR file AND use loglib version 2.0.0 which is how it is coded.

So to do this I removed the `<relocation>` definition from the `helloworld/pom.xml` file
and added a maven-shade-plugin configuration to the `goodlib/pom.xml` file.

The full POM is at  [../goodlib/pom.xml](../goodlib/pom.xml)

### Now build it and run it
The program now runs properly and output is shown below
```
mvn clean install
# Now run it
java -jar helloworld\target\helloworld-1.0.0.jar
Hello World!
What's up, Sam
Hello, Sam
Goodbye, Sam
```
As you can see, no runtime exception is thrown.

### Examining the jar file produced
When examining the jar file produced I see that it created the correct
result
```
jar tf goodlib\target\goodlib-1.0.0.jar
META-INF/
META-INF/MANIFEST.MF
com/steranka/play/GoodFeature.class
META-INF/maven/
META-INF/maven/com.steranka.play/goodlib/pom.xml
META-INF/maven/com.steranka.play/goodlib/pom.properties
com/shaded/steranka/play/LogIt.class
META-INF/maven/com.steranka.play/loglib/pom.xml
META-INF/maven/com.steranka.play/loglib/pom.properties
```
Notice there is no `com.steranka.play.LogIt` class in that file.

Now when I examine the main program's UberJar I see:
```
META-INF/MANIFEST.MF
META-INF/
com/steranka/play/HelloWorldApp.class
META-INF/maven/com.steranka.play/helloworld/pom.xml
META-INF/maven/com.steranka.play/helloworld/pom.properties
com/steranka/play/GoodFeature.class
META-INF/maven/com.steranka.play/goodlib/pom.xml
META-INF/maven/com.steranka.play/goodlib/pom.properties
com/shaded/steranka/play/LogIt.class
META-INF/maven/com.steranka.play/loglib/
META-INF/maven/com.steranka.play/loglib/pom.xml
META-INF/maven/com.steranka.play/loglib/pom.properties
com/steranka/play/LogIt.class
```

Notice that both versions of the `LogIt` class exist.
To prove this I would need to run javap on the class file to see the signature
so this is shown next:
```
jar xf helloworld-1.0.0.jar
javap com.shaded.steranka.play.LogIt
Compiled from "LogIt.java"
public class com.shaded.steranka.play.LogIt {
  public com.shaded.steranka.play.LogIt();
  public java.lang.String sayHello(java.lang.String);
}

javap com.steranka.play.LogIt
Compiled from "LogIt.java"
public class com.steranka.play.LogIt {
  public com.steranka.play.LogIt();
  public java.lang.String sayHello(java.lang.String, java.lang.String);
}
```

So there it is.  I have two versions of a library in the same JAR file.

### Does it run inside the IDE?
I'm using IntelliJ Ultimate and found that this app would not run
inside of IntelliJ.  I'm pretty sure it's because IntelliJ is using the source code
for the entire project and it doesn't know about the shading that was done in the
`goodlib.jar` file.

What is even more confusing is that if you click for the definitions, IntellIJ properly jumps you to the 
correct code, but at runtime it can't include both versions of the `LogIt` class and hence fails with
an exception.

# Reflection on my problem

To me it was not intuitive that I had to change the goodlib.jar file because
in my real-life example, I'm using a JAR file that I got from a 3rd party
and I don't have the code to change it.

So I still need to figure out how to get the shade plugin to work as in my first attempt,
or find a way to shade the 3rd party dependency without having the code to build it.