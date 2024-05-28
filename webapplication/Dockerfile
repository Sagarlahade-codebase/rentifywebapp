#
# Build stage
#
FROM maven:3-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY . .

# Package the application
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17

# Copy the jar file from the build stage
COPY --from=build /app/target/spring-boot-docker.jar demo.jar

# Set environment variable for the port (optional)
# ENV PORT=

# Expose the port on which the application will run
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "demo.jar","Dockerfile"]
