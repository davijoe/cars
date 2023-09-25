# Use the official maven/Java 17 base image
FROM maven:3.8.3-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and source code to the container
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use OpenJDK JRE base image
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /app/target/car-0.0.1-SNAPSHOT.jar /app.jar

# Set the entry point
ENTRYPOINT ["java", "-jar", "/app.jar"]
