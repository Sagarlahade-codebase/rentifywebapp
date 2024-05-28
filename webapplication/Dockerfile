# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Package stage
FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app
COPY --from=build /app/target/spring-boot-docker.jar demo.jar   
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
