# D0020E Project
![build](https://github.com/Cebasthian/d0020e-project/actions/workflows/maven.yml/badge.svg)

---

# PostgreSQL database
Install PostgreSQL.
Create a user named `dpp` with password `pass`.
Create each database with `dpp` as owner.

1. Download Postgresql from [their website](https://www.postgresql.org/download/).
2. Start a `psql` shell and connect to postgres using the superuser credentials used when installing.
3. Run `CREATE USER dpp WITH PASSWORD 'pass';`
4. Run `CREATE DATABASE dpp_{module} OWNER dpp;` for each module to create its database.

# EDC Connector
See [Edc Connector](edc-connector/README.md).