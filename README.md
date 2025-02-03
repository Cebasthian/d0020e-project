# D0020E Project
![build](https://github.com/Cebasthian/d0020e-project/actions/workflows/maven.yml/badge.svg)

---

## 1. Installation

We supply a `docker-compose.yml` so that you can simply build the project using `maven clean package` and then `docker-compose up -d --build`.

---

### 1.1 (alt) PostgreSQL Database
1. Make sure you have a PostgreSQL instance up and running.
2. Create a user with the name `dpp` and password `pass`, `CREATE USER dpp WITH PASSWORD 'pass';`.
3. Create the databases using `CREATE DATABASE dpp_{module} OWNER dpp;` where `{module}` is `manufacturer`, `recycler`, and `supplier`.

### 1.2 (alt)  Edc Connectors
See [Edc Connectors](edc-connector/README.md) for more information on how to start without docker.  

### 1.3 (alt) Spring boot modules
Simply open the project in IntelliJ IDEA, wait for maven dependencies to download, and run the configuration `All Modules`.

## 2. Usage
To be written 

Links: 
* [Edc provider](http://localhost:8081/edc-provider/index.html) 
* [Edc consumer](http://localhost:8083/edc-consumer/index.html)