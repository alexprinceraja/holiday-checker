package com.example.holiday_checker.service;

import com.example.holiday_checker.model.Holiday;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
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

    @CircuitBreaker(name = "holidayChecker", fallbackMethod = "fallbackHolidays")
    @Retry(name = "holidayChecker")
    @RateLimiter(name = "holidayChecker")
    @Bulkhead(name = "holidayChecker")
    @Cacheable(value = "holidayCache", key = "#year + '-' + #country")
    public List<Holiday> getHolidays(int year, String country) {
        try {
            return webClient.get()
                    .uri("/PublicHolidays/{year}/{country}", year, country)
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

    // Fallback method must match signature + exception
    public String fallbackHolidays(int year, String country, Throwable t) {
        return "Fallback: unable to fetch holidays for " + country + " in " + year;
    }
}
