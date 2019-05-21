package com.wpam;

import java.util.HashMap;
import java.util.Map;

public class SupportedSources {
    private String[] sources;
    private Map<String, String> sourcesApi;

    public SupportedSources() {
        sources = new String[] {
                "source",
                "ABC News",
                "Al Jazeera English",
                "Ars Technica",
                "BBC News",
                "Bloomberg",
                "Business Insider",
                "CBS News",
                "CNN",
                "Google News",
                "Hacker News",
                "reddit/r/all",
                "Reuters",
                "Tech Crunch",
                "The Wall Street Journal",
                "The Washington Post",
        };
        sourcesApi = new HashMap<>();
        sourcesApi.put(sources[0], "abc-news");
        sourcesApi.put(sources[1], "al-jazeera-english");
        sourcesApi.put(sources[2], "ars-technica");
        sourcesApi.put(sources[3], "bbc-news");
        sourcesApi.put(sources[4], "bloomberg");
        sourcesApi.put(sources[5], "business-insider");
        sourcesApi.put(sources[6], "cbs-news");
        sourcesApi.put(sources[7], "cnn");
        sourcesApi.put(sources[8], "google-news");
        sourcesApi.put(sources[9], "hacker-news");
        sourcesApi.put(sources[10], "reddit-r-all");
        sourcesApi.put(sources[11], "reuters");
        sourcesApi.put(sources[12], "techcrunch");
        sourcesApi.put(sources[13], "the-wall-street-journal");
        sourcesApi.put(sources[14], "the-washington-post");
    }

    public String[] getSources() {
        return sources;
    }

    public String[] getSourcesAPI() {
        return sourcesApi.values().toArray(new String[sourcesApi.size()]);
    }
}
