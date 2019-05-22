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

    public int getIndex(String category) {
        for(int i=0;i<categories.length;i++) {
            if(categories[i].equals(category))
                return i;
        }
        return 0;
    }
}
