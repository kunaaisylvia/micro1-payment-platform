# Micro1 Backend Java — Payment Microservices

A compact Spring Boot microservices project designed as a portfolio and interview project for backend engineering roles.

## Services
- **payment-service (8081):** creates and retrieves payments; demonstrates idempotency keys and validation.
- **wallet-service (8082):** wallet creation and credit operations.
- **notification-service (8083):** notification API boundary for asynchronous/event-driven extension.

## Architecture
Client → REST APIs → independent Spring Boot services → service-owned data layer.

The first iteration intentionally uses in-memory persistence so service boundaries and API behavior are easy to understand. PostgreSQL, Kafka/RabbitMQ, API Gateway, JWT/OAuth2, Resilience4j, and OpenTelemetry are planned next iterations.

## Run
Requires Java 21 and Maven 3.9+.

```bash
mvn clean package
cd payment-service && java -jar target/payment-service-0.1.0.jar
```

Or package all services and run them with Docker Compose.

## Example
```bash
curl -X POST http://localhost:8081/api/v1/payments \
  -H 'Content-Type: application/json' \
  -d '{"idempotencyKey":"order-1001","payerId":"user-1","merchantId":"merchant-9","amount":25.00,"currency":"USD"}'
```

## Interview topics demonstrated
- Spring Boot application structure
- REST API design
- DTO validation
- Global exception handling
- Idempotency
- Microservice boundaries
- Health endpoints
- Containerization
- Scalability and resilience trade-offs
