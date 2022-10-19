# mintyn-inventory-report-management

This is a Sales/Inventory-Report Distributed system Application

It is built with the combination of both the Event Driven Microservices Architecture and
the Service Oriented Architecture.

### Technologies

- Java
- Maven
- RabbitMQ
- MySQL
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Eureka

### Requirements

You need the following to build and run the application:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.6](https://maven.apache.org) (This is the build tool used.)

[//]: # (- [Docker]&#40;https://www.docker.com/products/docker-desktop/&#41;)

## How to run Application(s)
### step 1 - clone project with Terminal from [here](https://github.com/TryG70/Melita-OrderAPI.git)

```
git clone https://github.com/TryG70/Melita-OrderAPI.git
```

### step 2 - move into the project directory
```
cd order-api/
```

### step 3 - Open the order-api Folder in an IDE, As a maven Project.

### step 4 - Run the eureka-server application module first 

```
cd eureka-server/
``` 
```
mvn spring-boot:run
```
### step 5 - To run the other modules, Run the following commands in the following order just as the eureka server was run.

- api-gateway
- config-server
- order-taking-api
- order-approval-api
- order-fulfillment-api
- mail-service



### step 6 - Generate the .jar files with Terminal

```
mvn clean install 
```
OR
```
./mvnw clean install
```

## Here's the Link to the externalized configuration for Spring Cloud Config [here](https://github.com/TryG70/Melita-Config-File.git)
```
git clone https://github.com/TryG70/Melita-Config-File.git
```


## Postman Collection forIntegration Tests
- Melita Order API [here](https://www.getpostman.com/collections/7595dcb52b220242cb95)


## Running The Tests with Maven

To run the tests with maven, you would need to run the maven command for testing, after the code has been compiled.
```
mvn <option> test
```
- If "mvn" doesn't work, please use "./mvnw".

#### ReportService Test
- All Tests in ReportServiceImplTest can be run using
```
mvn -Dtest=ReportServiceImplTest test
```

#### OrderService Test
- All Tests in OrderServiceImplTest can be run using
```
mvn -Dtest=OrderServiceImplTest test
```

#### ProductService Test
- All Tests in ProductServiceImplTest can be run using
```
mvn -Dtest=ProductServiceImplTest test
```
