# jLiner
Library to efficiently handle large multiline strings in Java. Useful for large SQL queries.

## Installation
Currently pushed as a netbeans project, to make it easier to clone and build.

If you don't use netbeans you can just use the jar built in the 'dist' folder.

## Usage

JLiner works by reading strings from XML files stored on the classpath. 

These are XML files in name only, they simple use the XML tag format to identify different named strings, see below:

```xml
<myExampleSQLString>
select * from 
dual
</myExampleSQLString>

<some-json>
{
    name:"Jo",
    surname:"Bloggs"
}
</some-json>

<can.be.CALLED.whatever.youLike>
This is
a
multiline string
</can.be.CALLED.whatever.youLike>
```

Usage is simple. After including the JAR in your project, 
create a custom class that extends the JLinerConfig class and sets an array of 'FileLocation' instances in the constructor.

These are the locations of your XML files that store the long strings. 

You don't have to have multiple files, but it can be useful to logically separate strings.

A FileLocation takes a file name and a package name using dot notation.

```java
public class FooJLinerConfig extends JLinerConfig {

    public FooJLinerConfig() {
        super();
        this.setCustomLocations(new FileLocation[]{
            new FileLocation("MySQLQueries.xml", "org.foo.jlinertexts"),
            new FileLocation("MyLoveLetters.xml", "org.foo.jlinertexts")
        });
    }

}
```

Then, once your custom config class is setup, create a class that extends JLiner and uses the custom config.

This will then be your class used to fetch the strings in your Java code.


```java
public class FooJLiner extends JLiner {

    public FooJLiner() {
        super(new RingingJLinerConfig());
    }
      
}
```

It can then be used as follows:

```java
FooJLinerConfig myjLiner = new FooJLinerConfig();

String myString = jLiner.fetch("myExampleSQLString");
```

And that's it.

## Singleton

You could consider making the custom class a singleton to make the code simpler.

```java
public class FooJLiner extends JLiner {
    
    private static final FooJLiner instance;
    
    private FooJLiner() {
        super(new RingingJLinerConfig());
        this.instance = null;
    }
    
    public static fetchString(String key) {
      if( this.instance==null ) {
        this.instance = new FooJLiner();
      }
      return this.instance.fetch(key);
    }
    
}
```
