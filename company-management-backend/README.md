# Company Management - Server Side App

## Prerequisites

* **Oracle Database 11g+**
* **Java8**
* **Apache Maven**

If you are using Oracle on local on Remote, remember to check the JDBC URL and modify if necessary in application.properties file.
Create a DB User with cm/cm. Run the create_tables.sql script to create the table structures.

## Maven Commands

Compile Sources
```
mvn compile
```

Package App Without Tests
```
mvn package -DskipTests=true
```

Tests App
```
mvn tests
```
