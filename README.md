# VulnAPI

**VulnAPI** is a Spring Boot application that fetches CVE data from the NVD API, stores it in a database, and exposes RESTful APIs to query vulnerabilities by CVE ID or last modified date. It demonstrates modular design, pagination, and real-world backend practices.

---

## Features
- Fetch CVE data from the NVD API with pagination
- Store CVE data in a relational database (MySQL/PostgreSQL)
- REST API to retrieve CVE by **CVE ID**
- REST API to retrieve CVEs modified in the last **N days**
- Modular architecture (Controller → Service → Repository → Entity)
- Upsert logic to update existing CVEs

---

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- RESTful APIs
- MySQL/PostgreSQL (configurable)
- Jackson for JSON parsing
- Maven

---

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/cve/fetch` | POST | Fetch CVE data from NVD API and store in DB |
| `/cve/id/{cveId}` | GET | Retrieve CVE details by CVE ID |
| `/cve/modified?days={n}` | GET | Retrieve CVEs modified in the last N days |

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/VulnVault.git

2.  Configure database in application.properties:
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/cvedb
    spring.datasource.username=root
    spring.datasource.password=1234
3.  Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
4.  Use Postman or curl to test the API endpoints.

---
   
## Notes

Ensure your database is running before starting the app.

Large CVE datasets may take time to fetch; consider batch inserts for efficiency.

Date parsing uses ISO-8601 format from NVD API.

---

## Author

SELVA GANESH V

---

## License

This project is licensed under the MIT License.
