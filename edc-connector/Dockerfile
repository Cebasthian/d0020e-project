# Use an OpenJDK base image
FROM eclipse-temurin:21-jre-jammy AS build

# Set the working directory
WORKDIR /app

# Copy the connector JAR and configs into the container
COPY build/libs/connector.jar .
COPY configs/ configs/

# Expose any necessary ports (replace 8080 with your application's port if different)
EXPOSE 8080

# Default command (can be overridden by Docker Compose)
CMD ["java", "-Dedc.keystore=certs/cert.pfx", "-Dedc.keystore.password=123456", "-Dedc.fs.config=configs/default.properties", "-jar", "connector.jar"]
