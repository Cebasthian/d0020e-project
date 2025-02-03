# D0020E Project
![build](https://github.com/Cebasthian/d0020e-project/actions/workflows/maven.yml/badge.svg)

---

## 1. Installation Docker

1. Run `mvn clean package` in root folder.
2. Go into `edc-connector` folder and run `./gradlew clean build`.
3. Start containers with `docker-compose up -d --build` in root folder.

## 2. Installation (alt)

### 2.1 PostgreSQL Database
1. Make sure you have a PostgreSQL instance up and running.
2. Create a user with the name `dpp` and password `pass`, `CREATE USER dpp WITH PASSWORD 'pass';`.
3. Create the databases using `CREATE DATABASE dpp_{module} OWNER dpp;` where `{module}` is `manufacturer`, `recycler`, and `supplier`.

### 2.2 Edc Connectors
See [Edc Connectors](edc-connector/README.md) for more information on how to start without docker.  

### 2.3 Spring boot modules
Simply open the project in IntelliJ IDEA, wait for maven dependencies to download, and run the configuration `All Modules`.

## 3. Usage
To be written 

Links: 
* [Edc provider](http://localhost:8081/edc-provider/index.html) 
* [Edc consumer](http://localhost:8083/edc-consumer/index.html)