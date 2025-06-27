# Use a minimal base image with Java 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Spring Boot JAR into the container
COPY target/scaling-advisor-1.0.0.jar app.jar


# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
