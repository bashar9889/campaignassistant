package com.example.campaignassistant.infrastructure.ai.impl;

import com.example.campaignassistant.domain.enums.RuleTriggerType;
import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.infrastructure.ai.CampaignCopyGenerator;
import com.example.campaignassistant.infrastructure.ai.GeneratedCampaignDraft;
import com.example.campaignassistant.infrastructure.ai.PromptBuilder;
import org.springframework.stereotype.Component;

@Component
public class MockCampaignCopyGenerator implements CampaignCopyGenerator {

    private final PromptBuilder promptBuilder;

    public MockCampaignCopyGenerator(PromptBuilder promptBuilder) {
        this.promptBuilder = promptBuilder;
    }

    @Override
    public GeneratedCampaignDraft generateCopy(Business business, CampaignRule rule) {
        // In a real implementation, we'd call an AI API with the prompt.
        // Here we generate simple mock content based on the rule.

        String subject;
        String bodyIntro;

        if (rule.getTriggerType() == RuleTriggerType.NEW_CUSTOMER) {
            subject = "Welcome to " + business.getName() + "!";
            bodyIntro = "Thanks for choosing " + business.getName() + ".";
        } else {
            int days = rule.getTriggerValue();
            if (days <= 14) {
                subject = "We miss seeing you at " + business.getName();
                bodyIntro = "It's been a little while since your last visit.";
            } else {
                subject = "A special offer from " + business.getName();
                bodyIntro = "We'd love to see you again, so we have a little something for you.";
            }
        }

        String body = bodyIntro + "\n\n"
                + "We’re a local " + business.getNiche()
                + " serving " + business.getTargetAudience() + ".\n"
                + "Drop by " + business.getLocation() + " and say hi!\n\n"
                + "Best,\n"
                + business.getName();

        // You could log the prompt if you want:
        // String prompt = promptBuilder.buildPrompt(business, rule);

        return new GeneratedCampaignDraft(subject, body);
    }
}
