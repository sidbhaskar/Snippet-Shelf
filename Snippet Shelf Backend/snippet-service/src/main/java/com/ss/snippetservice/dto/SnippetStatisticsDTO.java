package com.ss.snippetservice.dto;

import java.util.Map;

public class SnippetStatisticsDTO {
    private long totalSnippets;
    private long totalFavorites;
    private long totalTags;
    private Map<String, Long> languageCounts;
    private Map<String, Long> tagCounts;

    public SnippetStatisticsDTO() {}

    public SnippetStatisticsDTO(long totalSnippets, long totalFavorites, long totalTags,
                                Map<String, Long> languageCounts, Map<String, Long> tagCounts) {
        this.totalSnippets = totalSnippets;
        this.totalFavorites = totalFavorites;
        this.totalTags = totalTags;
        this.languageCounts = languageCounts;
        this.tagCounts = tagCounts;
    }

    // Getters and Setters
    public long getTotalSnippets() { return totalSnippets; }
    public void setTotalSnippets(long totalSnippets) { this.totalSnippets = totalSnippets; }

    public long getTotalFavorites() { return totalFavorites; }
    public void setTotalFavorites(long totalFavorites) { this.totalFavorites = totalFavorites; }

    public long getTotalTags() { return totalTags; }
    public void setTotalTags(long totalTags) { this.totalTags = totalTags; }

    public Map<String, Long> getLanguageCounts() { return languageCounts; }
    public void setLanguageCounts(Map<String, Long> languageCounts) { this.languageCounts = languageCounts; }

    public Map<String, Long> getTagCounts() { return tagCounts; }
    public void setTagCounts(Map<String, Long> tagCounts) { this.tagCounts = tagCounts; }
}