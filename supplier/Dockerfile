FROM openjdk:21-jdk-slim

COPY edc-connector/build/libs/connector.jar /app/connector.jar
COPY supplier/src/main/resources/docker.properties /application.properties
COPY supplier/target/supplier-0.0.1-SNAPSHOT.jar /app/module.jar
COPY edc-connector/docker-configs/supplier.properties /app/configs/supplier.properties

CMD ["sh", "-c", "java \
  -Dedc.keystore=/app/certs/cert.pfx \
  -Dedc.keystore.password=123456 \
  -Dedc.fs.config=/app/configs/supplier.properties \
  -jar /app/connector.jar & \
  java -jar /app/module.jar & \
  tail -f /dev/null"]