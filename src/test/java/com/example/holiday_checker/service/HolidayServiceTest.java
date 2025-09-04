package com.example.holiday_checker.service;

import com.example.holiday_checker.model.Holiday;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HolidayServiceTest {

    @Test
    void testGetHolidays() {
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);

        // Mock the WebClient response

        Holiday holiday = new Holiday();
        holiday.setDate(LocalDate.of(2024, 1, 1));
        holiday.setName("New Year");
        holiday.setLocalName("New Year");
        when(webClient.get().uri("/PublicHolidays/{year}/{country}", 2024, "US")
                .retrieve()
                .bodyToFlux(Holiday.class)
                .collectList()
                .block()).thenReturn(List.of(holiday));

        HolidayService service = new HolidayService(webClient);
        List<Holiday> result = service.getHolidays(2024, "US");

        assertEquals(1, result.size());
        assertEquals("New Year", result.get(0).getName());
    }
}
