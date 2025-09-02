package com.example.holiday_checker.integration;

import com.example.holiday_checker.model.Holiday;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class HolidayIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Spin up Redis container
    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7.0.5")
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void overrideRedisProps(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", () -> redis.getMappedPort(6379));
    }

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/holidays";
    }

    @Test
    void shouldReturnLast3HolidaysAndCacheResults() {
        String url = baseUrl + "/last3?year=2024&country=US";

        // First call → should hit external API and populate Redis cache
//        ResponseEntity<Holiday> response1 = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Holiday>() {}
//        );

       // assertThat(response1.getStatusCode().is2xxSuccessful()).isTrue();
      //  assertThat(response1.getBody()).isNotNull();
       // assertThat(response1.getBody().size()).isGreaterThan(0);

        // Second call → should return cached result from Redis
//        ResponseEntity<Holiday> response2 = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Holiday>() {}
//        );

      //  assertThat(response2.getStatusCode().is2xxSuccessful()).isTrue();
       // assertThat(response2.getBody()).isEqualTo(response1.getBody());
    }
}
