# Use the official openjdk base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target folder into the container
COPY target/notification-service.jar notification-service.jar

# Expose the port the app runs on
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
