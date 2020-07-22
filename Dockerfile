FROM maven:3.6-adoptopenjdk-11 AS base

RUN curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt-get install -y curl nodejs

COPY .mvn /ap/.mvn
COPY src /app/src
COPY pom.xml /app/pom.xml

RUN cd /app && mvn package -DskipTests=true

RUN cp "/app/target/$(ls /app/target | grep exec.jar)" /tmp/hotel-booking.jar

FROM openjdk:11.0-jre

COPY --from=0 /tmp/hotel-booking.jar /app/hotel-booking.jar

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/ || exit 1

CMD ["java", "-jar", "/app/hotel-booking.jar"]
