package com.example.holiday_checker.service;

import com.example.holiday_checker.model.Holiday;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HolidayServiceTest {

    @Test
    void testGetHolidays() {
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);

        // Mock the WebClient response
        Holiday h = new Holiday();
        h.setDate(LocalDate.of(2024, 1, 1));
        h.setName("New Year");
        h.setLocalName("New Year");

        when(webClient.get().uri("/PublicHolidays/{year}/{country}", 2024, "US")
                .retrieve()
                .bodyToFlux(Holiday.class)
                .collectList()
                .block()).thenReturn(List.of(h));

        HolidayService service = new HolidayService(webClient);
        List<Holiday> result = service.getHolidays(2024, "US");

        assertEquals(1, result.size());
        assertEquals("New Year", result.get(0).getName());
    }
}
