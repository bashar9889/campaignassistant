package com.example.campaignassistant.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "generated_campaigns")
public class GeneratedCampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_rule_id", nullable = false)
    private CampaignRuleEntity campaignRule;

    private String subject;

    @Column(length = 4000)
    private String body;

    @Column(name = "generated_at")
    private Instant generatedAt;

    @Column(name = "model_info")
    private String modelInfo;

    public GeneratedCampaignEntity() {
    }

    public Long getId() {
        return id;
    }

    public BusinessEntity getBusiness() {
        return business;
    }

    public CampaignRuleEntity getCampaignRule() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusiness(BusinessEntity business) {
        this.business = business;
    }

    public void setCampaignRule(CampaignRuleEntity campaignRule) {
        this.campaignRule = campaignRule;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setModelInfo(String modelInfo) {
        this.modelInfo = modelInfo;
    }
}
