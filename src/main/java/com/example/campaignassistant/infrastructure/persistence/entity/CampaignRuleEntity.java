package com.example.campaignassistant.infrastructure.persistence.entity;

import com.example.campaignassistant.domain.enums.CampaignChannel;
import com.example.campaignassistant.domain.enums.RuleTriggerType;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "campaign_rules")
public class CampaignRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "trigger_type")
    private RuleTriggerType triggerType;

    @Column(name = "trigger_value")
    private int triggerValue;

    @Enumerated(EnumType.STRING)
    private CampaignChannel channel;

    private boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    public CampaignRuleEntity() {
    }

    public Long getId() {
        return id;
    }

    public BusinessEntity getBusiness() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusiness(BusinessEntity business) {
        this.business = business;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTriggerType(RuleTriggerType triggerType) {
        this.triggerType = triggerType;
    }

    public void setTriggerValue(int triggerValue) {
        this.triggerValue = triggerValue;
    }

    public void setChannel(CampaignChannel channel) {
        this.channel = channel;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
