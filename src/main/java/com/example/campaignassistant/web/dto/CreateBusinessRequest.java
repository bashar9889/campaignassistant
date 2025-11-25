package com.example.campaignassistant.web.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateBusinessRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String niche;

    @NotBlank
    private String targetAudience;

    @NotBlank
    private String toneOfVoice;

    private String location;

    public CreateBusinessRequest() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNiche(String niche) {
        this.niche = niche;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setToneOfVoice(String toneOfVoice) {
        this.toneOfVoice = toneOfVoice;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
