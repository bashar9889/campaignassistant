package com.example.campaignassistant.application.mapper;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.domain.model.GeneratedCampaign;
import com.example.campaignassistant.infrastructure.persistence.entity.GeneratedCampaignEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneratedCampaignMapper {

    private final BusinessMapper businessMapper;
    private final CampaignRuleMapper campaignRuleMapper;

    public GeneratedCampaignMapper(BusinessMapper businessMapper,
                                   CampaignRuleMapper campaignRuleMapper) {
        this.businessMapper = businessMapper;
        this.campaignRuleMapper = campaignRuleMapper;
    }

    public GeneratedCampaign toDomain(GeneratedCampaignEntity entity) {
        if (entity == null) {
            return null;
        }
        Business business = businessMapper.toDomain(entity.getBusiness());
        CampaignRule rule = campaignRuleMapper.toDomain(entity.getCampaignRule());

        return new GeneratedCampaign(
                entity.getId(),
                business,
                rule,
                entity.getSubject(),
                entity.getBody(),
                entity.getGeneratedAt(),
                entity.getModelInfo()
        );
    }
}
