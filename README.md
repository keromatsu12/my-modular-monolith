# Modular Monolith with Spring Modulith

This repository demonstrates a **Modular Monolith** architecture implemented in Kotlin using Spring Boot and Spring Modulith. It emphasizes **Clean Architecture** principles within each module to ensure maintainability and testability.

[日本語 (Japanese)](./README_ja.md)

## Key Features

- **Modular Monolith**: The application is structured as a monolith but composed of loosely coupled modules (`catalog`, `order`, etc.).
- **Clean Architecture**: Each module follows Clean Architecture principles, separating concerns into Domain, Application, Infrastructure, and Presentation layers.
- **Spring Modulith**: Uses Spring Modulith to verify module boundaries and manage dependencies, ensuring that modules do not violate architectural constraints.
- **Dependency Control**: Public APIs are explicitly defined, while internal implementation details are hidden, enforcing strong encapsulation.

## Project Structure

The project is organized into the following modules:

- **app**: The main application entry point (Spring Boot Application).
- **modules**:
  - **catalog**: Manages product catalog (Clean Architecture example).
    - `domain`: Core business logic and entities.
    - `application`: Use cases and application services.
    - `infrastructure`: Implementation of interfaces (repositories, external services).
    - `presentation`: REST controllers.
  - **order**: Manages orders.
  - **common**: Shared utilities and cross-cutting concerns.

## Technology Stack

- **Language**: Kotlin 1.9.24
- **Framework**: Spring Boot 3.3.0
- **Architecture Validation**: Spring Modulith 1.2.0
- **Database**: PostgreSQL 16
- **Build Tool**: Gradle 8.8
- **Containerization**: Docker & Docker Compose

## Getting Started

### Prerequisites

- Docker and Docker Compose installed.
- JDK 21 (if running locally without Docker).

### Running with Docker

You can easily start the application and the database using Docker Compose:

```bash
docker-compose up --build
```

The application will be available at `http://localhost:8080`.

### Running Tests

To run the tests, including the Spring Modulith verification tests:

```bash
./gradlew test
```

## Architecture Details

Each module is designed to be independent. The `app` module aggregates all feature modules to create the runtime artifact.

### Dependency Rule
- **Domain** layer does not depend on any other layer.
- **Application** layer depends on Domain.
- **Infrastructure** and **Presentation** layers depend on Application and Domain.

Spring Modulith ensures that no cyclic dependencies exist and that modules only access allowed public components of other modules.
