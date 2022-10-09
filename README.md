# Practice Spring

This is a kotlin application used by Tony Mowers to demonstrate some application development concepts.

## Requirements

The application uses kotlin, maven and java version 17.

## Start Application Using Maven 

If you use SDKMAN to setup you Java environment:
```
sdk install java 17.0.3.6.1-amzn 
```

Run application using maven:
```
mvn spring-boot:run
```

## Invoke REST API

```
curl localhost:8080/fizzbuzz/1
```

The REST call should return "1".