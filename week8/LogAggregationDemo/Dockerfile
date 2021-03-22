FROM maven:3.6.1-jdk-8 as builder
WORKDIR /app

COPY pom.xml pom.xml
COPY src/ src/

RUN mvn clean package -Dmaven.test.skip=true

# This stage will download the Pinpoint APM agent from an available source
# and extract the archive to provide the .jar file and needed dependencies
FROM ubuntu:20.10 as extractor

WORKDIR /pinpoint-agent

# Download the archive for the pinpoint agent directly
ADD https://github.com/pinpoint-apm/pinpoint/releases/download/v2.2.2/pinpoint-agent-2.2.2.tar.gz pinpoint-agent-2.2.2.tar.gz

# Extract the archive
RUN tar -xzf pinpoint-agent-2.2.2.tar.gz

FROM java:8 as runner

WORKDIR /app

# Copy the pinpoint agent to this stage
COPY --from=extractor /pinpoint-agent/pinpoint-agent-2.2.2/ /pinpoint-agent
COPY pinpoint.config /pinpoint-agent/pinpoint.config

# Copy Artifact from builder stage
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-javaagent:/pinpoint-agent/pinpoint-bootstrap-2.2.2.jar", "-Dpinpoint.config=/pinpoint-agent/pinpoint.config", "-Dpinpoint.agentId=log-aggregation", "-Dpinpoint.applicationName=log-aggregation", "-jar", "app.jar"]