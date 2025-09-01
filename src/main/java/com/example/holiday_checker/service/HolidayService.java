package com.example.holiday_checker.service;

import com.example.holiday_checker.model.Holiday;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class HolidayService {

    private final WebClient webClient;

    public HolidayService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Holiday> getHolidays(int year, String countryCode) {
        try {
            return webClient.get()
                    .uri("/PublicHolidays/{year}/{country}", year, countryCode)
                    .retrieve()
                    .bodyToFlux(Holiday.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e) {
            // This will be handled by GlobalExceptionHandler
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch holidays: " + e.getMessage(), e);
        }
    }
}
