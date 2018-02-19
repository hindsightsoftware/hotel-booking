# Hotel-booking

Hotel booking is a test app used for BDD tooling training. It is a simple API with a user interface that allows you to create, read, update and delete bookings.

Simply download the JAR file from the [releases page](https://github.com/hindsightsoftware/hotel-booking/releases) and run:

```java -jar hotel-booking-x.x.jar```

You will be able to access the UI via [localhost:8080](#) as well as view the Swagger API documentation via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Developing

To develop Hotel booking further you will need to go through a couple of initial steps:

Add the ApprovalTest jar file to your local maven repo using the following command in the root project folder:

```mvn install:install-file -Dfile=./lib/approvaltests.jar -DgroupId=org.approvaltests -DartifactId=approvaltests -Dversion=0.0.19 -Dpackaging=jar```
 
