# PayFlow — Java Spring Boot Microservices Payment Platform

A production-style Spring Boot microservices payment platform built as an interview and portfolio project for backend engineering roles.

## Architecture

```text
                         ┌───────────────┐
                         │  API Gateway  │ :8080
                         └───────┬───────┘
                                 │
                ┌────────────────┼────────────────┐
                ▼                ▼                ▼
        ┌──────────────┐ ┌──────────────┐ ┌────────────────┐
        │   Payment    │ │    Wallet    │ │ Notification   │
        │   Service    │ │   Service    │ │    Service     │
        │    :8081     │ │    :8082     │ │     :8083      │
        └──────┬───────┘ └──────┬───────┘ └───────▲────────┘
               │                 │                 │
          PostgreSQL         PostgreSQL         Kafka events
```

## Services
- **api-gateway:** single entry point and routing boundary.
- **payment-service:** payment creation/retrieval, idempotency, persistence, JWT security, resilience and event publishing.
- **wallet-service:** persistent wallet balances with transactional credit/debit operations.
- **notification-service:** consumes payment events asynchronously through Kafka.

## Engineering Features
- Java 21 + Spring Boot 3.5
- RESTful APIs and DTO validation
- PostgreSQL + Spring Data JPA/Hibernate
- Kafka event-driven communication
- JWT authentication with stateless security
- Resilience4j circuit breaker
- OpenAPI/Swagger
- Actuator health/metrics endpoints
- Docker + Docker Compose
- Unit testing with JUnit/Mockito
- GitHub Actions CI
- Service-owned persistence and clear microservice boundaries

## Run locally

Requires Java 21, Maven 3.9+, and Docker.

```bash
mvn clean verify
docker compose up --build
```

Gateway: `http://localhost:8080`
Payment API: `http://localhost:8081`
Wallet API: `http://localhost:8082`
Notification API: `http://localhost:8083`
Swagger: `http://localhost:8081/swagger-ui.html`

## Authentication

For local development, obtain a JWT:

```bash
curl -X POST http://localhost:8080/api/v1/auth/token \
  -H 'Content-Type: application/json' \
  -d '{"username":"tinah"}'
```

Use the returned token as `Authorization: Bearer <token>` for protected payment endpoints.

## Example payment

```bash
curl -X POST http://localhost:8080/api/v1/payments \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  -d '{"idempotencyKey":"order-1001","payerId":"user-1","merchantId":"merchant-9","amount":25.00,"currency":"USD"}'
```

A repeated request with the same idempotency key returns the existing payment rather than creating a duplicate transaction.

## Interview talking points

This project demonstrates practical decisions around service boundaries, database ownership, idempotent payment APIs, asynchronous events, stateless authentication, resilience, observability, containerization, testing, and CI/CD.

For production, the next hardening steps would include a managed Kafka/PostgreSQL setup, secrets management, mTLS/service identity, distributed tracing, an outbox pattern for guaranteed event publication, stronger authorization/RBAC, database migrations with Flyway, and load testing.
