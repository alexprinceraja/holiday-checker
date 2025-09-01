# Holiday Checker Spring Boot Application

This is a Spring Boot application that retrieves public holiday information using the [Nager.Date API](https://date.nager.at/Api). The application supports retrieving the last 3 holidays, counting non-weekend holidays per country, and finding shared holidays between countries.

The project uses **WebClient**, **Spring Boot**, **Swagger UI**, and includes **unit and integration tests**.

---

## Features

1. **Get last 3 celebrated holidays for a country**
2. **Count non-weekend holidays for one or more countries**
3. **Get shared holidays between two countries**
4. **Custom error handling** via `@RestControllerAdvice`
5. **Swagger UI** for API documentation
6. **Unit tests and integration tests** using JUnit, Mockito, and MockWebServer

---

## Technology Stack

- Java 17
- Spring Boot 3.x
- Spring WebFlux (`WebClient`)
- Swagger/OpenAPI (`springdoc-openapi`)
- JUnit 5 & Mockito for testing
- MockWebServer for integration testing

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Internet access to fetch Nager.Date API

### Clone the repository

```bash
git clone https://github.com/your-username/holiday-checker.git
cd holiday-checker
