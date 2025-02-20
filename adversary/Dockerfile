FROM openjdk:21-jdk-slim

COPY edc-connector/build/libs/connector.jar /app/connector.jar
COPY adversary/src/main/resources/docker.properties /application.properties
COPY adversary/target/adversary-0.0.1-SNAPSHOT.jar /app/module.jar
COPY edc-connector/docker-configs/adversary.properties /app/configs/adversary.properties

CMD ["sh", "-c", "java \
  -Dedc.keystore=/app/certs/cert.pfx \
  -Dedc.keystore.password=123456 \
  -Dedc.fs.config=/app/configs/adversary.properties \
  -jar /app/connector.jar & \
  java -jar /app/module.jar & \
  tail -f /dev/null"]