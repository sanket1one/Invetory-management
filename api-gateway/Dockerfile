# Stage 1: Build the project
FROM maven:3.9.0 AS builder

WORKDIR /app
# Copy only pom.xml first to leverage Docker cache
COPY pom.xml .
# Download dependencies (this layer will be cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application, skipping tests for faster build (remove -DskipTests if needed)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jdk
WORKDIR /app
# Copy the jar built in the first stage
COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]