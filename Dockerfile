# ==============================
# STAGE 1: Build the application
# ==============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Maven configuration and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the JAR file, skipping tests for speed
RUN mvn clean package -DskipTests

# ============================
# STAGE 2: Run the application
# ============================
FROM eclipse-temurin:21-jdk-alpine

# Create app directory
WORKDIR /app

# Copy built JAR from the first stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
