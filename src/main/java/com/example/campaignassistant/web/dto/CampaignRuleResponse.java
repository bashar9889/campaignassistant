package com.example.campaignassistant.web.dto;

public class CampaignRuleResponse {

    private Long id;
    private String name;
    private String triggerType;
    private int triggerValue;
    private String channel;
    private boolean active;

    public CampaignRuleResponse(Long id,
                                String name,
                                String triggerType,
                                int triggerValue,
                                String channel,
                                boolean active) {
        this.id = id;
        this.name = name;
        this.triggerType = triggerType;
        this.triggerValue = triggerValue;
        this.channel = channel;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public int getTriggerValue() {
        return triggerValue;
    }

    public String getChannel() {
        return channel;
    }

    public boolean isActive() {
        return active;
    }
}
