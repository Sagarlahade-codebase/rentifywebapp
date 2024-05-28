# Use Maven to build the project
FROM maven:3.8.5-openjdk-17 AS build
COPY pom.xml .
COPY src ./src
COPY Dockerfile Dockerfile
RUN mvn clean package -DskipTests

# Use a minimal JDK runtime image to run the application
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/spring-boot-docker.jar /demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=render", "-jar", "/demo.jar"]
