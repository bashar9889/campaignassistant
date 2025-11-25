package com.example.campaignassistant.infrastructure.ai;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;

public interface CampaignCopyGenerator {

    GeneratedCampaignDraft generateCopy(Business business, CampaignRule rule);
}
