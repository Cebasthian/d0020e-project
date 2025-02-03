# D0020E Project
![build](https://github.com/Cebasthian/d0020e-project/actions/workflows/maven.yml/badge.svg)

---

## 1. Installation

### 1.1 PostgreSQL Database
TODO: docker container
1. Make sure you have a PostgreSQL instance up and running.
2. Create a user with the name `dpp` and password `pass`, `CREATE USER dpp WITH PASSWORD 'pass';`.
3. Create the databases using `CREATE DATABASE dpp_{module} OWNER dpp;` where `{module}` is `manufacturer`, `recycler`, and `supplier`.

### 1.2 Edc Connectors
The connectors are run via docker containers. Simply run `docker compose up` in root.

See [Edc Connectors](edc-connector/README.md) for more information on how to start without docker.  

### 1.3 Spring boot modules
TODO: docker containers

Simply open the project in IntelliJ IDEA, wait for maven dependencies to download, and run the configuration `All Modules`.

## 2. Usage
To be written