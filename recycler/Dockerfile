FROM openjdk:21-jdk-slim

COPY edc-connector/build/libs/connector.jar /app/connector.jar
COPY recycler/target/recycler-0.0.1-SNAPSHOT.jar /app/module.jar
COPY edc-connector/configs/recycler.properties /app/configs/recycler.properties

CMD ["sh", "-c", "java \
  -Dedc.keystore=/app/certs/cert.pfx \
  -Dedc.keystore.password=123456 \
  -Dedc.fs.config=/app/configs/recycler.properties \
  -jar /app/connector.jar & \
  java -jar /app/module.jar & \
  tail -f /dev/null"]