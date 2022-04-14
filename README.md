


### The project directory structure
src
+ test
  + java
    + cts.ui
      + actions                 customize actions
      + model                   category definitions
      + steps                   step definitions
      + tasks                   task,target definitions
      CucumberTestSuite         test runner config
    + lib                       library definitions
  + resources
    + features                  feature files
    + testdata                  data files
    + webdriver                 driver files
    + serenity.conf             driver, report configurations
    + resource.properties       property definitions
+ pom.xml                       config maven project


## Executing the tests using maven
$ mvn clean verify

$ mvn clean verify -Ddriver=chrome

$ mvn clean verify -Ddriver=firefox

$ mvn clean verify -Denvironment=local3

$ mvn clean verify -Ddriver=chrome -Denvironment=local1

The test results will be recorded in the `target/site/serenity` directory.

## Environment requirement
+ make sure you have a JDK 1.8 or later installed
+ using Maven 3.3.x or higher installed
