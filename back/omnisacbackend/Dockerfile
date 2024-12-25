# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source code into the container
COPY . /app

# Make the Gradle wrapper executable
RUN chmod +x /app/gradlew

# Build the application using the Gradle wrapper
RUN ./gradlew build

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "build/libs/omnisacbackend-0.0.1-SNAPSHOT.jar"]