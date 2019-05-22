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
        sourcesApi.put(sources[0], "bbc");
        sourcesApi.put(sources[1], "abc-news");
        sourcesApi.put(sources[2], "al-jazeera-english");
        sourcesApi.put(sources[3], "ars-technica");
        sourcesApi.put(sources[4], "bbc-news");
        sourcesApi.put(sources[5], "bloomberg");
        sourcesApi.put(sources[6], "business-insider");
        sourcesApi.put(sources[7], "cbs-news");
        sourcesApi.put(sources[8], "cnn");
        sourcesApi.put(sources[9], "google-news");
        sourcesApi.put(sources[10], "hacker-news");
        sourcesApi.put(sources[11], "reddit-r-all");
        sourcesApi.put(sources[12], "reuters");
        sourcesApi.put(sources[13], "techcrunch");
        sourcesApi.put(sources[14], "the-wall-street-journal");
        sourcesApi.put(sources[15], "the-washington-post");
    }

    public String[] getSources() {
        return sources;
    }

    public String[] getSourcesAPI() {
        return sourcesApi.values().toArray(new String[sourcesApi.size()]);
    }

    public String getCode(String source) {
        return sourcesApi.get(source);
    }

    public int getIndex(String source) {
        for(int i=0;i<sources.length;i++) {
            if(sources[i].equals(source))
                return i;
        }
        return 0;
    }
}
