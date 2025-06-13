# Build stage
FROM maven AS build
WORKDIR /app
COPY . /app
RUN mvn clean install

# Package stage
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "aitgbot-0.0.1-SNAPSHOT.jar"]