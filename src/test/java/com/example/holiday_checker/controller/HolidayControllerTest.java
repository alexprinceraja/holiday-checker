package com.example.holiday_checker.controller;

import com.example.holiday_checker.model.Holiday;
import com.example.holiday_checker.processor.HolidayProcessor;
import com.example.holiday_checker.service.HolidayService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HolidayControllerTest {

    @Test
    void testGetLast3() {
        HolidayService service = mock(HolidayService.class);
        HolidayProcessor processor = new HolidayProcessor();

        HolidayController controller = new HolidayController(service, processor);

        Holiday h1 = new Holiday();
        h1.setDate(LocalDate.of(2024,1,1));
        h1.setName("New Year");
        Holiday h2 = new Holiday();
        h2.setDate(LocalDate.of(2024,7,4));
        h2.setName("Independence Day");

        when(service.getHolidays(2024, "US")).thenReturn(List.of(h1,h2));

        List<Holiday> last3 = controller.getLast3(2024,"US");
        assertEquals(2, last3.size());
        assertEquals("Independence Day", last3.get(0).getName());
    }
}
