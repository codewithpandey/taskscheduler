# ----------- STAGE 1: Build the JAR inside Docker -------------
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /build

# Copy all project files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Make gradlew executable
RUN chmod +x gradlew

# Build using bootJar
RUN ./gradlew bootJar --no-daemon

# ----------- STAGE 2: Run the Spring Boot JAR ----------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy only the final JAR from the builder stage
COPY --from=builder /build/build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
