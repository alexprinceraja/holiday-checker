package com.example.holiday_checker.processor;

import com.example.holiday_checker.model.Holiday;
import com.example.holiday_checker.dto.NonWeekendHolidayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HolidayProcessorTest {

    private HolidayProcessor processor;

    @BeforeEach
    void setup() {
        processor = new HolidayProcessor();
    }

    @Test
    void testGetLast3Holidays() {
        List<Holiday> holidays = Arrays.asList(
                createHoliday("2024-01-01", "New Year"),
                createHoliday("2024-05-01", "Labor Day"),
                createHoliday("2024-07-04", "Independence Day"),
                createHoliday("2024-12-25", "Christmas")
        );

        List<Holiday> last3 = processor.getLast3Holidays(holidays);
        assertEquals(3, last3.size());
        assertEquals("2024-12-25", last3.get(0).getDate().toString());
        assertEquals("2024-07-04", last3.get(1).getDate().toString());
        assertEquals("2024-05-01", last3.get(2).getDate().toString());
    }

    @Test
    void testGetNonWeekendHolidays() {
        Map<String, List<Holiday>> data = new HashMap<>();
        data.put("US", Arrays.asList(
                createHoliday("2024-01-01", "New Year"),   // Monday
                createHoliday("2024-01-06", "WeekendHoliday") // Saturday
        ));

        Map<String, NonWeekendHolidayResponse> result = processor.getNonWeekendHolidays(data);
        assertTrue(result.containsKey("US"));
        NonWeekendHolidayResponse us = result.get("US");
        assertEquals(1, us.getCount());
        assertEquals("2024-01-01", us.getHolidays().get(0).getDate().toString());
    }

    @Test
    void testGetSharedHolidays() {
        List<Holiday> c1 = Arrays.asList(
                createHoliday("2024-01-01", "New Year"),
                createHoliday("2024-07-04", "Independence Day")
        );

        List<Holiday> c2 = Arrays.asList(
                createHoliday("2024-01-01", "New Year"),
                createHoliday("2024-12-25", "Christmas")
        );

        List<Holiday> shared = processor.getSharedHolidays(c1, c2);
        assertEquals(1, shared.size());
        assertEquals("2024-01-01", shared.get(0).getDate().toString());
    }

    private Holiday createHoliday(String date, String name) {
        Holiday h = new Holiday();
        h.setDate(LocalDate.parse(date));
        h.setName(name);
        h.setLocalName(name);
        return h;
    }
}
