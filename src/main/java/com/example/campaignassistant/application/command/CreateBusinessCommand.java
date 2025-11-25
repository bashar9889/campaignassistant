package com.example.campaignassistant.application.command;

import com.example.campaignassistant.domain.enums.ToneOfVoice;

public class CreateBusinessCommand {

    private final String name;
    private final String niche;
    private final String targetAudience;
    private final ToneOfVoice toneOfVoice;
    private final String location;

    public CreateBusinessCommand(String name,
                                 String niche,
                                 String targetAudience,
                                 ToneOfVoice toneOfVoice,
                                 String location) {
        this.name = name;
        this.niche = niche;
        this.targetAudience = targetAudience;
        this.toneOfVoice = toneOfVoice;
        this.location = location;
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
}
