package com.example.holiday_checker.controller;

import com.example.holiday_checker.model.Holiday;
import com.example.holiday_checker.processor.HolidayProcessor;
import com.example.holiday_checker.service.HolidayService;
import com.example.holiday_checker.dto.NonWeekendHolidayResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService service;
    private final HolidayProcessor processor;

    public HolidayController(HolidayService service, HolidayProcessor processor) {
        this.service = service;
        this.processor = processor;
    }

    @GetMapping("/last3")
    @Operation(summary = "Get last 3 celebrated holidays for a country")
    public List<Holiday> getLast3(
            @RequestParam int year,
            @RequestParam String country) {
        List<Holiday> holidays = service.getHolidays(year, country);
        return processor.getLast3Holidays(holidays);
    }

    @GetMapping("/non-weekend")
    @Operation(summary = "Count holidays not falling on weekends for given countries, including dates")
    public Map<String, NonWeekendHolidayResponse> countNonWeekend(
            @RequestParam int year,
            @RequestParam List<String> countries) {

        Map<String, List<Holiday>> map = new HashMap<>();
        for (String c : countries) {
            map.put(c, service.getHolidays(year, c));
        }
        return processor.getNonWeekendHolidays(map);
    }

    @GetMapping("/shared")
    @Operation(summary = "Get shared holidays between two countries")
    public List<Holiday> getShared(
            @RequestParam int year,
            @RequestParam String country1,
            @RequestParam String country2) {

        List<Holiday> c1 = service.getHolidays(year, country1);
        List<Holiday> c2 = service.getHolidays(year, country2);
        return processor.getSharedHolidays(c1, c2);
    }
}
