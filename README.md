# Webhook Forge

Webhook Forge is a lightweight backend service for receiving and storing webhook events through dynamically generated endpoints.

The project is built as a practical Java backend pet project focused on HTTP integrations, persistence, event processing, reliability, and observability.

## Current Features

* Create webhook endpoints
* Generate endpoint identifiers using PostgreSQL UUIDv7
* Receive webhook payloads through public endpoint URLs
* Store raw webhook request bodies in PostgreSQL
* Associate received events with their webhook endpoint
* Automatically store event creation timestamps
* Validate endpoint existence before accepting events

## How It Works

1. Create a webhook endpoint:

```text
POST /api/v1/endpoints
```

2. Webhook Forge creates an endpoint with a unique UUIDv7 identifier.

Example:

```text
/h/019...
```

3. Configure an external service to send webhook requests to this URL.

4. When a POST request is received:

```text
POST /h/{token}
```

Webhook Forge:

* finds the corresponding endpoint;
* captures the raw request body;
* creates a webhook event;
* stores it in PostgreSQL.

## Example Flow

```text
External Service
      |
      | POST webhook
      v
/h/{token}
      |
      v
Webhook Forge
      |
      +--> Find WebhookEndpoint
      |
      +--> Create WebhookEvent
      |
      v
PostgreSQL
```

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Jakarta Validation
* Lombok
* Maven

## Domain Model

### WebhookEndpoint

Represents a public webhook destination.

Currently contains:

* UUIDv7 identifier
* name

### WebhookEvent

Represents a webhook request received by an endpoint.

Currently contains:

* event ID
* related webhook endpoint
* raw request body
* creation timestamp

## API

### Create Endpoint

```text
POST /api/v1/endpoints
```

Example request:

```json
{
  "name": "GitHub webhook"
}
```

### Get Endpoint

```text
GET /api/v1/endpoints/{id}
```

### Receive Webhook

```text
POST /h/{token}
```

The request body is stored as a raw payload so Webhook Forge is not tied to a specific webhook provider or JSON schema.

## Running Locally

Requirements:

* Java
* PostgreSQL
* Maven

Create a PostgreSQL database:

```text
webhook_forge
```

Configure the database connection in `application.properties`.

Then start the application.

The API will be available at:

```text
http://localhost:8080
```

## Project Status

The project is currently in early development.

Implemented:

```text
Endpoint creation
        |
        v
Webhook reception
        |
        v
Event persistence
```

## Planned Features

Future versions are planned to include:

* webhook event history API
* HTTP headers capture
* HTTP method and content type storage
* webhook forwarding
* delivery attempt history
* manual webhook replay
* automatic retries
* exponential backoff
* asynchronous delivery
* RabbitMQ
* dead-letter queues
* API authentication
* rate limiting
* metrics and monitoring
* Docker support
* integration tests with Testcontainers

## Project Goals

Webhook Forge is designed to explore real backend engineering problems rather than being a simple CRUD application.

The project will gradually cover topics such as:

* domain modeling
* database design
* transactions
* external HTTP communication
* asynchronous processing
* retry strategies
* idempotency
* message queues
* security
* failure handling
* observability
* integration testing

## License

This project is currently developed as a personal educational project.
