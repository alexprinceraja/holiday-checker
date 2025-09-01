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
  
### Build & Run

mvn clean install
mvn spring-boot:run

Application will start at:
http://localhost:8080

### API Endpoints
All endpoints are available via Swagger UI:
- http://localhost:8080/swagger-ui.html

<details> <summary>Get last 3 celebrated holidays for a country</summary>
  
    GET /holidays/last3?year={year}&country={countryCode}
   ##### Parameters:
    - year (int) - e.g., 2024
    - country (string) - ISO 3166-1 alpha-2 country code (e.g., US, GB)
      
</details> <details> <summary>Count non-weekend holidays for one or more countries</summary> 
  
    GET /holidays/non-weekend?year={year}&countries={country1},{country2},...
  
</details> <details> <summary>Get shared holidays between two countries</summary>
  
    GET /holidays/shared?year={year}&country1={country1}&country2={country2}
  
</details> <details> <summary>Custom error handling** via `@RestControllerAdvice`</summary>
  
    The application uses a global exception handler with structured error responses:
  
</details> <details> <summary>Unit tests and integration tests** using JUnit, Mockito, and MockWebServer`</summary> 
  
    Run all unit and integration tests:
  
</details>

### Clone the repository

```bash
git clone https://github.com/your-username/holiday-checker.git
cd holiday-checker

