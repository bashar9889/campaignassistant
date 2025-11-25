package com.example.campaignassistant.domain.model;

import java.time.Instant;

public class GeneratedCampaign {

    private Long id;
    private Business business;
    private CampaignRule campaignRule;
    private String subject;
    private String body;
    private Instant generatedAt;
    private String modelInfo;

    public GeneratedCampaign() {
    }

    public GeneratedCampaign(Long id,
                             Business business,
                             CampaignRule campaignRule,
                             String subject,
                             String body,
                             Instant generatedAt,
                             String modelInfo) {
        this.id = id;
        this.business = business;
        this.campaignRule = campaignRule;
        this.subject = subject;
        this.body = body;
        this.generatedAt = generatedAt;
        this.modelInfo = modelInfo;
    }

    public Long getId() {
        return id;
    }

    public Business getBusiness() {
        return business;
    }

    public CampaignRule getCampaignRule() {
        return campaignRule;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public String getModelInfo() {
        return modelInfo;
    }
}
