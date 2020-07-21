# Hotel-booking [![CircleCI](https://circleci.com/gh/hindsightsoftware/hotel-booking.svg?style=svg)](https://circleci.com/gh/hindsightsoftware/hotel-booking)

Hotel booking is a test app used for BDD tooling training. It is a simple API with a user interface that allows you to create, read, update and delete bookings.

## Run via Maven

You will need to have `mvn` (version 3.5 or later), `npm`, and `node` (version 14.x) installed.

```bash
mvn spring-boot:run
```

You will be able to access the UI via [localhost:8080](#) (login via username `admin` and password `password123`) as well as view the Swagger API documentation via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
 
## Run via Docker

Use the Dockerfile in this repository that will build an image with the app, or use the `docker-compose.yml` file which does it automatically for you:

```bash
docker-compose up
```
