FROM openjdk:21-jdk-slim

COPY edc-connector/build/libs/connector.jar /app/connector.jar
COPY manufacturer/src/main/resources/docker.properties /application.properties
COPY manufacturer/target/manufacturer-0.0.1-SNAPSHOT.jar /app/module.jar
COPY edc-connector/docker-configs/manufacturer.properties /app/configs/manufacturer.properties

CMD ["sh", "-c", "java \
  -Dedc.keystore=/app/certs/cert.pfx \
  -Dedc.keystore.password=123456 \
  -Dedc.fs.config=/app/configs/manufacturer.properties \
  -jar /app/connector.jar & \
  java -jar /app/module.jar & \
  tail -f /dev/null"]