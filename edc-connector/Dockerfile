FROM eclipse-temurin:21-jre-jammy AS build

WORKDIR /app

COPY build/libs/connector.jar .
COPY configs/ configs/

CMD ["java", "-Dedc.keystore=certs/cert.pfx", "-Dedc.keystore.password=123456", "-Dedc.fs.config=configs/default.properties", "-jar", "connector.jar"]
