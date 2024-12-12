# Use official OpenJDK base image
FROM openjdk:17-jdk-slim

# Add metadata
LABEL maintainer="mail.festus@gmail.com"
LABEL version="1.0"
LABEL description="Clutch: Multi-Identity Service for Enterprise Security and Observability"

# Set the working directory
WORKDIR /app

# Copy the JAR file from build context
COPY build/libs/clutch-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
