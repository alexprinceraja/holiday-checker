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
   ##### Example:
      GET /holidays/last3?year=2024&country=US
   ##### Response:
      ```json
            [
              { "date": "2024-12-25", "localName": "Christmas Day", "name": "Christmas Day", "countryCode": "US" },
              { "date": "2024-07-04", "localName": "Independence Day", "name": "Independence Day", "countryCode": "US" }
            ]
        
 
</details> <details> <summary>Count non-weekend holidays for one or more countries</summary> 
  
    GET /holidays/non-weekend?year={year}&countries={country1},{country2},...
  ##### Example:
      GET /holidays/non-weekend?year=2024&countries=US,GB
  ##### Response:
       ```json
          {
          "US": {
            "count": 8,
            "holidays": [
              { "date": "2024-12-25", "localName": "Christmas Day", "name": "Christmas Day", "countryCode": "US" },
              { "date": "2024-07-04", "localName": "Independence Day", "name": "Independence Day", "countryCode": "US" }
            ]
          },
          "GB": {
            "count": 7,
            "holidays": [
              { "date": "2024-12-25", "localName": "Christmas Day", "name": "Christmas Day", "countryCode": "GB" }
            ]
          }
        }
    ```      
    - Holidays are sorted descending by date.
    - Countries are sorted by non-weekend holiday count descending.
    
</details> <details> <summary>Get shared holidays between two countries</summary>
  
    GET /holidays/shared?year={year}&country1={country1}&country2={country2}
  ##### Example:
      GET /holidays/shared?year=2024&country1=US&country2=GB   
  ##### Response:
      ```json
      [
        { "date": "2024-01-01", "localName": "New Year's Day", "name": "New Year's Day", "countryCode": "US" }
      ]
      
</details> <details> <summary>Custom error handling** via `@RestControllerAdvice`</summary>
  
    The application uses a global exception handler with structured error responses:
  ##### Response:
    ```json
    {
      "timestamp": "2025-09-01T12:34:56.789",
      "status": 404,
      "error": "External API Error",
      "message": "Not Found",
      "path": "uri=/holidays/last3?year=2024&country=XX"
    }
    ```
    
    - Handles WebClientResponseException from the external API.
    - Handles RuntimeException and IllegalArgumentException.
    - Centralized in @RestControllerAdvice for consistent JSON output.
    
</details> <details> <summary>Unit tests and integration tests** using JUnit, Mockito, and MockWebServer`</summary> 
  
  ##### Run all unit and integration tests:
    mvn test
  ##### Unit tests:
    - HolidayProcessorTest
    - HolidayServiceTest (mock WebClient)
    - HolidayControllerTest (mock service)
  ##### Integration tests:
    - @SpringBootTest
    - Uses MockWebServer to simulate external API responses
</details>

### Clone the repository

```bash
git clone https://github.com/your-username/holiday-checker.git
cd holiday-checker

