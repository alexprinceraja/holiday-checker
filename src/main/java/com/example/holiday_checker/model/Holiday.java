package com.example.holiday_checker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;

    @JsonProperty("date")
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @JsonProperty("localName")
    public String getLocalName() { return localName; }
    public void setLocalName(String localName) { this.localName = localName; }

    @JsonProperty("name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonProperty("countryCode")
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
}