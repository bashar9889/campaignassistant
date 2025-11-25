package com.example.campaignassistant.web.dto;

import java.time.Instant;

public class BusinessResponse {

    private Long id;
    private String name;
    private String niche;
    private String targetAudience;
    private String toneOfVoice;
    private String location;
    private Instant createdAt;

    public BusinessResponse(Long id,
                            String name,
                            String niche,
                            String targetAudience,
                            String toneOfVoice,
                            String location,
                            Instant createdAt) {
        this.id = id;
        this.name = name;
        this.niche = niche;
        this.targetAudience = targetAudience;
        this.toneOfVoice = toneOfVoice;
        this.location = location;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNiche() {
        return niche;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getToneOfVoice() {
        return toneOfVoice;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
