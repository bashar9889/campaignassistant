package com.example.campaignassistant.infrastructure.ai;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;

public interface PromptBuilder {

    String buildPrompt(Business business, CampaignRule rule);
}
