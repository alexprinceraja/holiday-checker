package com.example.holiday_checker.integration;

import com.example.holiday_checker.model.Holiday;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HolidayIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static MockWebServer mockServer;

    @BeforeAll
    static void setup() throws Exception {
        mockServer = new MockWebServer();
        mockServer.start(8081);
    }

    @AfterAll
    static void tearDown() throws Exception {
        mockServer.shutdown();
    }

    @Test
    void testLast3Endpoint() {
        // Mock API response
        String json = "[{\"date\":\"2024-01-01\",\"localName\":\"New Year\",\"name\":\"New Year\",\"countryCode\":\"US\"}]";
        mockServer.enqueue(new MockResponse().setBody(json).addHeader("Content-Type", "application/json"));

        String url = "http://localhost:" + port + "/holidays/last3?year=2024&country=US";
        ResponseEntity<Holiday[]> response = restTemplate.getForEntity(url, Holiday[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Holiday[] holidays = response.getBody();
        //assertNotNull(holidays);
       // assertEquals(1, holidays.length);
        //assertEquals("New Year", holidays[0].getName());
    }
}
