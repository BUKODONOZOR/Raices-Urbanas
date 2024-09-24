FROM maven:3-eclipse-temurin-21 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /target/raices-urbanas-0.0.1-SNAPSHOT.jar raices-urbanas.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "raices-urbanas.jar"]