package com.example.campaignassistant.domain.model;

import com.example.campaignassistant.domain.enums.ToneOfVoice;

import java.time.Instant;

public class Business {

    private Long id;
    private String name;
    private String niche;
    private String targetAudience;
    private ToneOfVoice toneOfVoice;
    private String location;
    private Instant createdAt;

    public Business() {
    }

    public Business(Long id,
                    String name,
                    String niche,
                    String targetAudience,
                    ToneOfVoice toneOfVoice,
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

    public ToneOfVoice getToneOfVoice() {
        return toneOfVoice;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
