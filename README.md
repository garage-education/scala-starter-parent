# Scala Starter Parent Project 

<img src="./images/ScalaParentStarter.png" height="128"> <a href="https://www.youtube.com/c/GarageEducation"><img src="./images/elep.png" height="128"></a>


[![License](http://img.shields.io/:license-Apache%202-green.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Latest Version](https://img.shields.io/github/v/release/garage-education/ScalaSandbox)](https://github.com/garage-education/scalasandbox/releases)


## Scla Parent Starter Project
- Create Scala SDLC.
- Simplify Scala project bootstrapping.
- Releasing strategy  

### pom.xml

This is our initial pom file version include the project pom details

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.gability.scala</groupId>
    <artifactId>scala-starter-parent</artifactId>
    <version>1.14-SNAPSHOT</version>
    <packaging>pom</packaging>
</project>

```

## Adding Dependencies for scala testing

In this part we add scala and scala-test libraries in the dependancy section in pom.xml


```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>   
    <groupId>com.gability.scala</groupId>
    <artifactId>scala-starter-parent</artifactId>
    <version>1.14-SNAPSHOT</version>
    <packaging>pom</packaging>
      <dependencies>
        <!-- tests -->
        <dependency>
            <groupId>org.scalatest</groupId>
            <artifactId>scalatest_${scala.version}</artifactId>
            <version>${scala.test.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.scala-lang/scala-library -->
        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-library</artifactId>
            <version>${scala.version}.${scala.sub.version}</version>
        </dependency>
    </dependencies>

        <properties>
        <!-- Plugins Versions -->
        <scala.sub.version>8</scala.sub.version>
        <scala.version>2.11</scala.version>
        <scala.test.version>3.0.5</scala.test.version>
        <!-- we will use the below plugings later -->
        <scala-maven-plugin-version>4.3.1</scala-maven-plugin-version>
        <scalatest-maven-plugin-version>2.0.0</scalatest-maven-plugin-version>
        <maven-compiler-plugin-version>3.1</maven-compiler-plugin-version>
        <java-version>1.8</java-version>
        <maven-surefire-plugin-version>2.12.4</maven-surefire-plugin-version>
        <maven.assembly.plugin.version>3.1.0</maven.assembly.plugin.version>
    </properties>

</project>

```

## Build, Compile, Execute Scala Unit testing, and Package the Jar using `mvn package`


### Add scala plugin to allow maven to compile scala code

```xml
<build>
    <plugins>
        <!-- maven plugin for scala compiler -->
        <plugin>
            <!-- https://mvnrepository.com/artifact/net.alchim31.maven/scala-maven-plugin -->
            <groupId>net.alchim31.maven</groupId>
            <artifactId>scala-maven-plugin</artifactId>
            <version>${scala-maven-plugin-version}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>testCompile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```

At this stage maven is able to compile scala sources and tests. However, maven still didn't run the unit testing still 0 test run.

### Add maven plugin for scala testing engine

Let's add maven plugin to run scala unit testing

```xml

<!-- maven plugin for scala testing engine -->
<plugin>
    <!-- https://mvnrepository.com/artifact/org.scalatest/scalatest-maven-plugin -->
    <groupId>org.scalatest</groupId>
    <artifactId>scalatest-maven-plugin</artifactId>
    <version>${scalatest-maven-plugin-version}</version>
    <executions>
        <execution>
            <goals>
                <goal>test</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Let's run and check the unit testing results. We can find that maven is able to run scala unit testing and provide the results.

### Skip java compilation for main and test

As we saw in the previous logs maven still assume our project contains java code. So, we will inform maven to skip any compilation for main and test java code. 

This can be achieved using `maven-compiler-plugin`. 

```xml
<plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>${maven-compiler-plugin-version}</version>
          <configuration>
              <source>${java-version}</source>
              <target>${java-version}</target>
              <skipMain>true</skipMain> <!-- skip java compile -->
              <skip>true</skip> <!-- skip java testCompile -->
          </configuration>
</plugin>

```

Let's check the output. we can find that maven skips the java comilation for main and test classes `Not compiling main sources` & `Not compiling test sources`

### Skip executing java unit testing

We can find the below part in the previous log which indicates that maven is trying to execute java unit testing in our code. Let's try to stop the below part which is not needed in our example.


We will add `maven-surefire-plugin` and skip java unit testing execution.

```xml
<!-- maven unit testing plugin-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven-surefire-plugin-version}/version>
    <configuration>
        <skipTests>true</skipTests>  <!-- skip java unit testing -->
    </configuration>
</plugin>

```
Let's run again and check the output. We can find that maven stop to execute java unit testing.

## Packaging the jar

```xml
<!-- Maven Assembly for building Jar With Dependencies -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>${maven.assembly.plugin.version}</version>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef> <!-- get the jar with dependencies -->
        </descriptorRefs>
        <archive>
            <manifest>
                <mainClass>com.gability.labs.cicd</mainClass> <!-- to inform the jar where is the main class demo the manifest without -->  
            </manifest>
        </archive>
        <appendAssemblyId>false</appendAssemblyId> <!-- to remove the descriptor from jar name --> 
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## Add Scala static analysis tools.

### Scoverage

Add code coverage tool for scala that offers statement and branch coverage **Scoverage**

```xml
 <!-- Maven scala coverage plugin -->
<plugin>
    <groupId>org.scoverage</groupId>
    <artifactId>scoverage-maven-plugin</artifactId>
    <version>${scoverage-plugin-version}</version>
    <configuration>
        <highlighting>true</highlighting>
        <minimumCoverage>${scoverage-minimumCoverage}</minimumCoverage>
        <failOnMinimumCoverage>true</failOnMinimumCoverage>
        <additionalForkedProjectProperties>skipTests=false</additionalForkedProjectProperties>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>check</goal>
            </goals>
            <phase>package</phase> <!-- must bind to a phase -->
        </execution>
    </executions>
</plugin>
```

### Findbugs
```xml
<!-- findbugs-maven-plugin -->
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>findbugs-maven-plugin</artifactId>
    <version>${findbugs-maven-plugin-version}</version>
    <configuration>
        <effort>Max</effort>
        <threshold>High</threshold>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### ScalaStyle

```xml
  <!-- Scala Style-maven-plugin-->
<plugin>
    <groupId>org.scalastyle</groupId>
    <artifactId>scalastyle-maven-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <verbose>false</verbose>
        <failOnViolation>true</failOnViolation>
        <includeTestSourceDirectory>true</includeTestSourceDirectory>
        <failOnWarning>false</failOnWarning>
        <sourceDirectory>${project.basedir}/src/main/scala</sourceDirectory>
        <testSourceDirectory>${project.basedir}/src/test/scala</testSourceDirectory>
        <outputFile>${project.build.directory}/checkstyle-result.xml</outputFile>
        <inputEncoding>${project.build.sourceEncoding}</inputEncoding>
        <outputEncoding>${project.reporting.outputEncoding}</outputEncoding>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>org.scalastyle</groupId>
            <artifactId>scalastyle-maven-plugin</artifactId>
            <version>${scalastyle-maven-plugin-version}</version>
        </dependency>
    </dependencies>
</plugin>
```

## Maven Reporting

```xml
<!-- Maven Reporting Site plugin-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-site-plugin</artifactId>
    <version>${maven-site-plugin-version}</version>
</plugin>

<!-- Maven Surefire Report Plugin-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-report-plugin</artifactId>
    <version>${maven-surefire-report-plugin-version}</version>
</plugin>
<!-- Maven Project Info Reports Plugin-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-project-info-reports-plugin</artifactId>
    <version>${project-info-reports.plugin-version}</version>
</plugin>

```
