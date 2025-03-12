# D0020E Project
![build](https://github.com/Cebasthian/d0020e-project/actions/workflows/maven.yml/badge.svg)

---

# Introduction
This project is a demonstration of dataspaces and how to integrate EDC connectors.
They key features are:
* Policy enforcement
* Admin webpages
* Edc Gui
* Multi-stakeholder demo

With this project we show how to implement and use EDC connectors to communicate and share data with different organizations. 
We utilize multiple modules that acts as stakeholders to simulate basic scalability. 
By using EDCs built-in policy engine with a custom evaluator we also provide policy enforcement on assets for security.
Our Edc Gui is used as a simple webpage to facilitate the communication with connectors for usability.

# Getting Started

## Installation
We provide a docker compose file to ease the usage of our project. But first we need to build the project.
1. Run maven package in root folder.
```shell
./mvnw clean package
```
2. Go into edc-connector folder and run gradle build.
```shell
cd edc-connector && ./gradlew clean build
```
3. Start containers with using the docker compose file in root folder.
```shell
docker-compose up -d --build
```

## Usage
Having the containers up and running you can navigate to any of the websites available on localhost.
E.g the supplier admin webpage: [http://localhost:8083](http://localhost:8083).

| Module       | Module Number | Webserver Port |
|--------------|---------------|----------------|
| Manufacturer | 0             | 8080           |
| Recycler     | 1             | 8081           |
| Adversary    | 2             | 8082           |
| Supplier     | 3             | 8083           |

See
[Edc Connectors](edc-connector/README.md)
for information on which ports and IP addresses the EDC connectors have in the docker network.

The Edc Gui is located under [http://localhost:PORT/edc](http://localhost:8080/edc).

## Demonstration
The following steps demonstrate how our project works and shows the key points of the features.
1. Navigate to the supplier admin webpage [http://localhost:8083](http://localhost:8083).
2. Scroll down to find the RTX 3080 gpu, remember the ID.
3. In the swagger documentation on [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)
shows that we can get a specific gpu on the endpoint [http://localhost:8083/gpus/{id}](http://localhost:8083/gpus/{id}).
4. Navigate to the Edc Gui [http://localhost:8083/edc](http://localhost:8083/edc). 
(If there is nothing on the Connectors page, there may be something wrong. Check logs)
5. Click on Assets and then Create New Asset. Fill the text fields with name = `RTX3080` and url = `http://localhost:8083/gpus/{id}` but with the id from step 2. Click on Create.
6. Copy the `asset-id` that has shown up.
7. Click on Policies and then Create New Policy. Type in to the text fields 
policy_id = `identity-policy`, attibute = `identity`, and value = `manufacturer` 
(note that if you want multiple modules to have access write them comma-separated, e.g. `manufacturer,recycler` ). Click on Create.
8. Create a contract. Fill in contract_id = `rtx3080_contract`, choose the identity policy in both contract and access policy.
Set attribute to `id` and value to the `asset-id` from step 6. Click Create.
9. Navigate to [Manufacturer Edc Gui](http://localhost:8080/edc).
10. Click on Catalog, the newly created contract should show up with participant_id = `supplier`.
11. Begin Negotiation and navigate to Negotiated Contracts.
12. Wait until the state is FINALIZED and then click on Transfer and navigate to Transfers.
13. Wait until state is STARTED. Now we can finally get the data on the gpu. Click on Retrieve Data. We should get the asset in JSON form. 
This shows the event flow from having data in our own database module-side to negotiating and transferring data via dataspace using EDC connectors to finally being able to read the resource.

[//]: # (## 1. Installation Docker)

[//]: # ()
[//]: # (1. Run `mvn clean package` in root folder.)

[//]: # (2. Go into `edc-connector` folder and run `./gradlew clean build`.)

[//]: # (3. Start containers with `docker-compose up -d --build` in root folder.)

[//]: # ()
[//]: # (## 2. Installation &#40;alt&#41;)

[//]: # ()
[//]: # (### 2.1 PostgreSQL Database)

[//]: # (1. Make sure you have a PostgreSQL instance up and running.)

[//]: # (2. Create a user with the name `dpp` and password `pass`, `CREATE USER dpp WITH PASSWORD 'pass';`.)

[//]: # (3. Create the databases using `CREATE DATABASE dpp_{module} OWNER dpp;` where `{module}` is `manufacturer`, `recycler`, and `supplier`.)

[//]: # ()
[//]: # (### 2.2 Edc Connectors)
[//]: # (See [Edc Connectors]&#40;edc-connector/README.md&#41; for more information on how to start without docker.  )

[//]: # ()
[//]: # (### 2.3 Spring boot modules)

[//]: # (Simply open the project in IntelliJ IDEA, wait for maven dependencies to download, and run the configuration `All Modules`.)

[//]: # ()
[//]: # (## 3. Usage)

[//]: # (To be written )

[//]: # ()
[//]: # (Links: )

[//]: # (* [Edc provider]&#40;http://localhost:8081/edc-provider/index.html&#41; )

[//]: # (* [Edc consumer]&#40;http://localhost:8083/edc-consumer/index.html&#41;)
