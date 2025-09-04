package com.example.holiday_checker.controller;

import com.example.holiday_checker.model.Holiday;
import com.example.holiday_checker.model.NonWeekendHolidayResponse;
import com.example.holiday_checker.processor.HolidayProcessor;
import com.example.holiday_checker.service.HolidayService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HolidayControllerTest {

    @Test
    void testGetLast3() {
        HolidayService service = mock(HolidayService.class);
        HolidayProcessor processor = new HolidayProcessor();

        HolidayController controller = new HolidayController(service, processor);

        Holiday h1 = new Holiday();
        h1.setDate(LocalDate.of(2024, 1, 1));
        h1.setName("New Year");

        Holiday h2 = new Holiday();
        h2.setDate(LocalDate.of(2024, 7, 4));
        h2.setName("Independence Day");

        when(service.getHolidays(2024, "US")).thenReturn(List.of(h1, h2));

        List<Holiday> last3 = controller.getLast3(2024, "US");

        assertEquals(2, last3.size());
        assertEquals("Independence Day", last3.get(0).getName()); // Processor sorts by date desc
    }

    @Test
    void testCountNonWeekend() {
        HolidayService service = mock(HolidayService.class);
        HolidayProcessor processor = new HolidayProcessor();

        HolidayController controller = new HolidayController(service, processor);

        Holiday weekendHoliday = new Holiday();
        weekendHoliday.setDate(LocalDate.of(2025, 1, 5)); // Sunday
        weekendHoliday.setName("Weekend Holiday");

        Holiday weekdayHoliday = new Holiday();
        weekdayHoliday.setDate(LocalDate.of(2025, 1, 6)); // Monday
        weekdayHoliday.setName("Weekday Holiday");

        when(service.getHolidays(2025, "US")).thenReturn(List.of(weekendHoliday, weekdayHoliday));

        Map<String, NonWeekendHolidayResponse> result =
                controller.countNonWeekend(2025, List.of("US"));

        assertTrue(result.containsKey("US"));
        NonWeekendHolidayResponse response = result.get("US");

        assertEquals(1, response.getCount());
        assertEquals("Weekday Holiday", response.getHolidays().get(0).getName());
    }

    @Test
    void testGetShared() {
        HolidayService service = mock(HolidayService.class);
        HolidayProcessor processor = new HolidayProcessor();

        HolidayController controller = new HolidayController(service, processor);

        Holiday h1 = new Holiday();
        h1.setDate(LocalDate.of(2025, 1, 1));
        h1.setName("New Year");

        when(service.getHolidays(2025, "US")).thenReturn(List.of(h1));
        when(service.getHolidays(2025, "IN")).thenReturn(List.of(h1));

        List<Holiday> shared = controller.getShared(2025, "US", "IN");

        assertEquals(1, shared.size());
        assertEquals("New Year", shared.get(0).getName());
    }
}

