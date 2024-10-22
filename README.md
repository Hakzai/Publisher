
# Publisher App

This is a publisher app for book storage control. It was proposed as a technical challenge from Serasa Experian managers.

Initial design is pretty simple: build a service as a RESTful CRUD API that will control both *Authors* and its *Books*. Design requisition states that an Author can be recorded without a book, but Books cannot be recorded without associated Author.

Requirements:
- API should be built under Spring Boot Framework;
- Code must follow a Layer Architecture pattern (such as MVC);
    - It must have a security layer (suggested to use Spring Security, but not only);
- It should persist data on a database (to be chosen from MySQL, PostgreSQL or H2);
- Code must have solid Error handling design;
- App must have unit tests using JUnit 5;
- Document the API using Swagger;
- Monitoring it with Actuator;
- It is required to follow GitFlow steps to publish on GitHub.

## Technologies Stack

**Front-end:** JavaFX, CSS

**Back-end:** Java with Spring (Spring Boot, Spring Security), MySQL, Apache Tomcat

**Testing:** JUnit 5

**Documentation:** Swagger

**Monitoring:** Actuator

**Versioning:** GitHub

## Roadmap

App under development. Below are the current milestones:

* [X]  Create the API using Spring Boot Framework
* [X]  Layer Architecture (using MVC)
* [ ]  **PLUS:** Create a JavaFX GUI to interact with the server through requests
* [ ]  Implement the security layer (using Spring Security)
* [X]  Use a relational persistance database (using MySQL running on WAMPP server)
* [X]  Design for error handling
* [ ]  Unit tests with JUnit 5
* [X]  API Documentation with Swagger
* [ ]  App monitoring with Actuator
* [X]  Published on GitHub

## Installing

Project is managed with maven.

It was written using:
*  OpenJDK 23.0.1
*  Apache Maven 3.9.9
*  Spring Boot 3.3.4
*  Apache Tomcat 9.0.96
*  MySQL 8.0.18

To build and run make sure you're using same versions (or higher, with compatibility).

Personal tools setup:
- Spring Tool Suite 4.25.0
- WAMPP Server 3.2.2.2
- Thunder client v2.28.0 (VSCode)

## Author

Project done by Isaac Alencar (Akeir Technology)
