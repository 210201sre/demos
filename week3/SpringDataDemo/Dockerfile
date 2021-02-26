FROM maven:3.6.3-openjdk-8 as builder

COPY pom.xml pom.xml
COPY src/ src/

RUN mvn clean package