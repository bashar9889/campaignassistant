package com.example.campaignassistant.web.dto;

import java.time.Instant;

public class GeneratedCampaignResponse {

    private Long id;
    private Long businessId;
    private Long ruleId;
    private String subject;
    private String body;
    private Instant generatedAt;

    public GeneratedCampaignResponse(Long id,
                                     Long businessId,
                                     Long ruleId,
                                     String subject,
                                     String body,
                                     Instant generatedAt) {
        this.id = id;
        this.businessId = businessId;
        this.ruleId = ruleId;
        this.subject = subject;
        this.body = body;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public Long getRuleId() {
        return ruleId;
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
}
