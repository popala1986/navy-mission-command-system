# 🛡️ Mission Service — Naval Mission Command System

This microservice is responsible for managing missions within a naval command and control system. It provides RESTful endpoints for mission operations, integrates with a MySQL database, and is fully containerized for deployment.

---

## ⚙️ Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Docker & Docker Compose**
- **MySQL**
- **JUnit 5 + WebTestClient**
- **Maven**

---

## 📁 Project Structure

mission-service/ 

├── controller/ # REST API layer 

├── service/ # Business logic 

├── repository/ # Data access layer 

├── model/ # JPA entities and enums 

├── config/ # Spring configuration classes 

├── integration/ # Integration tests 

├── unit/ # Unit tests 

├── resources/ # Properties and SQL scripts 

└── MissionServiceApplication.java

Kod

---

## 🧪 Testing

- `MissionControllerIntegrationTest` — verifies `/missions` endpoint using WebTestClient and SQL-based test data
- `MissionRepositoryTest` — validates repository behavior with real DB
- `MissionControllerTest`, `MissionServiceTest` — unit tests with mocks
- `MissionTest` — entity-level tests (e.g., builder, equals/hashCode)

Test profile uses:
- `application-test.properties`
- `schema-test.sql` and `data-test.sql`

---

## 🚀 Running the Service

### Prerequisites

- Java 17
- Maven
- Docker + Docker Compose

### Run with Docker Compose

```bash
docker-compose up --build
Services:

mysql — database with password Barcelona1899!

mission-service — Spring Boot application

Run Tests
bash
mvn clean test
🌐 Spring Profiles
Profile	File	Purpose
default	application.properties	Local/prod configuration
test	application-test.properties	Integration testing
🧭 Architecture Diagram (Textual)
Kod
[Client] → [MissionController] → [MissionService] → [MissionRepository] → [MySQL]
                                 ↑
                        [MissionConfig]
📌 Status
✅ Integration and unit tests in place ✅ Dockerized and profile-ready ✅ Clean, modular structure ✅ Ready for expansion (e.g., unit-service, auth-service, intel-servic
