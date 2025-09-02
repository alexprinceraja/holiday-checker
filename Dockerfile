# Use a lightweight OpenJDK image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the jar file built by Maven
COPY target/holiday-checker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
