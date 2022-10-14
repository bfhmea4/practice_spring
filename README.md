# Practice Spring

This is a kotlin application is used by Tony Mowers to demonstrate some application development concepts 
for BFH Project 1 students.

## Requirements

The application uses kotlin, maven and java version 17.

If you use [SDKMAN](https://sdkman.io/) to setup you Java environment:
```
sdk install java 17.0.3.6.1-amzn 
```

If you don't use SDKMAN then you must use some other means to make sure your environment has
the proper JDK installed and accessible via the command line.

## Run Test Suites

To run the application's test suite:
```
mvn clean test
```

## Run Application Using Maven

### Start application

Start application by using maven:
```
mvn spring-boot:run
```

### Invoke REST API

Once the application is running you can test the REST API via:
```
curl localhost:8080/api/fizzbuzz/15
```

The REST call result should be {"n": 15, "result":"FizzBuzz"}.

## Docker Images

See [Spring Boot Docker](https://spring.io/guides/gs/spring-boot-docker/) webpage for more details.

### Build Local Image 

```
mvn spring-boot:build-image -DskipTests=True -Dspring-boot.build-image.imageName=tonymowers/practice
```

### Run Local Image

```
docker run -p 8080:8080 -t tonymowers/practice
```

Run test suite against localhost

```
mvn test -Dpractice.test.localhost=true
```