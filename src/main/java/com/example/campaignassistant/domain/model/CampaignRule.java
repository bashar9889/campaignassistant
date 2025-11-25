package com.example.campaignassistant.domain.model;

import com.example.campaignassistant.domain.enums.CampaignChannel;
import com.example.campaignassistant.domain.enums.RuleTriggerType;

import java.time.Instant;

public class CampaignRule {

    private Long id;
    private Business business;
    private String name;
    private RuleTriggerType triggerType;
    private int triggerValue;
    private CampaignChannel channel;
    private boolean active;
    private Instant createdAt;

    public CampaignRule() {
    }

    public CampaignRule(Long id,
                        Business business,
                        String name,
                        RuleTriggerType triggerType,
                        int triggerValue,
                        CampaignChannel channel,
                        boolean active,
                        Instant createdAt) {
        this.id = id;
        this.business = business;
        this.name = name;
        this.triggerType = triggerType;
        this.triggerValue = triggerValue;
        this.channel = channel;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Business getBusiness() {
        return business;
    }

    public String getName() {
        return name;
    }

    public RuleTriggerType getTriggerType() {
        return triggerType;
    }

    public int getTriggerValue() {
        return triggerValue;
    }

    public CampaignChannel getChannel() {
        return channel;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
