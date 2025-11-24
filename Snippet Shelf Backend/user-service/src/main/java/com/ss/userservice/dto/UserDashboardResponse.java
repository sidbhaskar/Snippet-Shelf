package com.ss.userservice.dto;

import java.util.UUID;

public class UserDashboardResponse {

    // From Auth Service (Passed via JWT/Header)
    private UUID userId;
    private String email;
    private String role;

    // From User Service (Database)
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
    private String avatarUrl;
    private String githubLink;

    // From Snippet Service (via OpenFeign)
    private long totalSnippets;
    private long totalFavorites;
    private long totalTagsUsed;
    private long totalCollections;


}
