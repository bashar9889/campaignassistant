package com.example.campaignassistant.infrastructure.ai.impl;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.infrastructure.ai.PromptBuilder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPromptBuilder implements PromptBuilder {

    @Override
    public String buildPrompt(Business business, CampaignRule rule) {
        StringBuilder sb = new StringBuilder();
        sb.append("Write a short marketing email for a local business.\n");
        sb.append("Business name: ").append(business.getName()).append("\n");
        sb.append("Niche: ").append(business.getNiche()).append("\n");
        sb.append("Target audience: ").append(business.getTargetAudience()).append("\n");
        sb.append("Tone of voice: ").append(business.getToneOfVoice()).append("\n");
        sb.append("Campaign type: ").append(rule.getName()).append("\n");
        sb.append("Trigger type: ").append(rule.getTriggerType()).append("\n");
        sb.append("Trigger value: ").append(rule.getTriggerValue()).append(" days\n");
        sb.append("Output an engaging subject line and a friendly body.\n");
        return sb.toString();
    }
}
