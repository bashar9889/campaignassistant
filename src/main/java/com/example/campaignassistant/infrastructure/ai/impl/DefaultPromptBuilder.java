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
        sb.append("You are an expert email marketing copywriter for small local businesses.\n");
        sb.append("Write ONE marketing email.\n\n");

        sb.append("Business name: ").append(business.getName()).append("\n");
        sb.append("Niche: ").append(business.getNiche()).append("\n");
        sb.append("Target audience: ").append(business.getTargetAudience()).append("\n");
        sb.append("Tone of voice: ").append(business.getToneOfVoice()).append("\n");
        sb.append("Location: ").append(business.getLocation()).append("\n\n");

        sb.append("Campaign name: ").append(rule.getName()).append("\n");
        sb.append("Trigger type: ").append(rule.getTriggerType()).append("\n");
        sb.append("Trigger value (days, if applicable): ").append(rule.getTriggerValue()).append("\n\n");

        sb.append("Write a short, friendly email that fits this campaign.\n");
        sb.append("Respond in EXACTLY this format:\n");
        sb.append("Subject: <single line subject>\n");
        sb.append("Body:\n");
        sb.append("<multi-line email body>\n");
        sb.append("If you need to think through the problem, use <think>...</think> tags, but do not include them in the final answer.\n");

        return sb.toString();
    }
}
