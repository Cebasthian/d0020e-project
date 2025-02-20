# EDC Connector
We set the ports as :1x00y where x is the module number and y is the specific port.

| Module       | Module_no | Api   | Public | Control | Management | Protocol | Version | Docker Ip address |
|--------------|-----------|-------|--------|---------|------------|----------|---------|-------------------|
| Manufacturer | 1         | 10000 | 10001  | 10002   | 10003      | 10004    | 10005   | 172.22.0.10       |
| Recycler     | 2         | 11000 | 11001  | 11002   | 11003      | 11004    | 11005   | 172.22.0.11       |
| Adversary    | 2         | 12000 | 12001  | 12002   | 12003      | 12004    | 12005   | 172.22.0.12       |
| Supplier     | 3         | 13000 | 13001  | 13002   | 13003      | 13004    | 13005   | 172.22.0.13       |

Run a connector by first building the project and then running the jar file generated. Note that these are not run in root but rather in `/edc-connector/`.
```
./gradlew clean build
```
Remember to change `module.properties` to the correct properties file. Possible files are `manufacturer.properties`, `recycler.properties`, and `supplier.properties`.
```
java -Dedc.keystore=certs/cert.pfx -Dedc.keystore.password=123456 -Dedc.fs.config=configs/module.properties -jar build/libs/connector.jar
```
