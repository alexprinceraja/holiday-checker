package com.example.holiday_checker.dto;

import com.example.holiday_checker.model.Holiday;

import java.util.List;

public class NonWeekendHolidayResponse {
    private long count;
    private List<Holiday> holidays;

    public NonWeekendHolidayResponse(long count, List<Holiday> holidays) {
        this.count = count;
        this.holidays = holidays;
    }

    public long getCount() {
        return count;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
