package com.wpam;

import java.util.HashMap;
import java.util.Map;

public class SupportedCountries {

    private String[] countries;
    private Map<String,String> countriesApi;

    public SupportedCountries() {
        countries = new String[] {
            "country",
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
        countriesApi.put(countries[0], "xx");
        countriesApi.put(countries[1], "us");
        countriesApi.put(countries[2], "pl");
        countriesApi.put(countries[3], "gb");
        countriesApi.put(countries[4], "my");
        countriesApi.put(countries[5], "id");
        countriesApi.put(countries[6], "fr");
        countriesApi.put(countries[7], "it");
        countriesApi.put(countries[8], "sg");
        countriesApi.put(countries[9], "nz");
        countriesApi.put(countries[10], "in");
    }

    public String getCode(String country) {
        return countriesApi.get(country);
    }

    public String[] getCountries() {
        return countries;
    }
}
