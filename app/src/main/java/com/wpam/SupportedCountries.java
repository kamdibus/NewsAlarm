package com.wpam;

import java.util.HashMap;
import java.util.Map;

public class SupportedCountries {

    private String[] countries;
    private Map<String,String> countriesApi;

    public SupportedCountries() {
        countries = new String[] {
            "United States",
            "Poland",
            "United Kingdom",
            "Malaysia",
            "Indonesia",
            "France",
            "Italy",
            "Singapore",
            "New Zealand",
            "India",
        };

        countriesApi = new HashMap<>();
        countriesApi.put(countries[0], "us");
        countriesApi.put(countries[1], "pl");
        countriesApi.put(countries[2], "gb");
        countriesApi.put(countries[3], "my");
        countriesApi.put(countries[4], "id");
        countriesApi.put(countries[5], "fr");
        countriesApi.put(countries[6], "it");
        countriesApi.put(countries[7], "sg");
        countriesApi.put(countries[8], "nz");
        countriesApi.put(countries[9], "in");
    }

    public String getCode(String country) {
        return countriesApi.get(country);
    }

    public String[] getCountries() {
        return countries;
    }
}
