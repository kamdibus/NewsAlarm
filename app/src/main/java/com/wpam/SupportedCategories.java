package com.wpam;

public class SupportedCategories {

    private String[] categories;

    public SupportedCategories() {
        categories = new String[] {
                "category",
                "business",
                "entertainment",
                "general",
                "health",
                "science",
                "sports",
                "technology",
        };
    }

    public String[] getCategories() {
        return categories;
    }
}
