package com.example.holiday_checker.processor;

import com.example.holiday_checker.model.Holiday;
import com.example.holiday_checker.dto.NonWeekendHolidayResponse;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HolidayProcessor {

    public List<Holiday> getLast3Holidays(List<Holiday> holidays) {
        LocalDate today = LocalDate.now();
        return holidays.stream()
                .filter(h -> h.getDate().isBefore(today))
                .sorted(Comparator.comparing(Holiday::getDate).reversed())
                .limit(3)
                .toList();
    }

    public Map<String, NonWeekendHolidayResponse> getNonWeekendHolidays(Map<String, List<Holiday>> countryHolidays) {
        return countryHolidays.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            List<Holiday> filtered = e.getValue().stream()
                                    .filter(h -> {
                                        DayOfWeek dow = h.getDate().getDayOfWeek();
                                        return dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY;
                                    })
                                    .sorted(Comparator.comparing(Holiday::getDate).reversed())
                                    .toList();
                            return new NonWeekendHolidayResponse(filtered.size(), filtered);
                        }
                ))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue().getCount(), a.getValue().getCount()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new
                ));
    }

    public List<Holiday> getSharedHolidays(List<Holiday> c1, List<Holiday> c2) {
        Set<LocalDate> c2Dates = c2.stream().map(Holiday::getDate).collect(Collectors.toSet());
        return c1.stream()
                .filter(h -> c2Dates.contains(h.getDate()))
                .collect(Collectors.toMap(Holiday::getDate, h -> h, (h1, h2) -> h1))
                .values().stream()
                .sorted(Comparator.comparing(Holiday::getDate))
                .toList();
    }
}
