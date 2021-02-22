FROM maven:3.6.3-openjdk-8 as builder
# Base image with jdk 8 and maven

# Copy our pom.xml and our source-code
COPY pom.xml pom.xml
COPY src/ src/

# Build our application
RUN mvn clean package

# As a separate stage, to save on resulting image size, we discard everything from previous stages
FROM java:8 as runner
# Base image only needs JRE 8

# Expose port 7000 for our web-app
EXPOSE 7000

# Copy the jar file from our previous stage
COPY --from=builder target/jdbc-demo-jar-with-dependencies.jar app.jar

# Run our program
ENTRYPOINT [ "java", "-jar", "app.jar" ]
