package com.example.campaignassistant.application.mapper;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.infrastructure.persistence.entity.CampaignRuleEntity;
import org.springframework.stereotype.Component;

@Component
public class CampaignRuleMapper {

    private final BusinessMapper businessMapper;

    public CampaignRuleMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    public CampaignRule toDomain(CampaignRuleEntity entity) {
        if (entity == null) {
            return null;
        }
        Business business = businessMapper.toDomain(entity.getBusiness());
        return new CampaignRule(
                entity.getId(),
                business,
                entity.getName(),
                entity.getTriggerType(),
                entity.getTriggerValue(),
                entity.getChannel(),
                entity.isActive(),
                entity.getCreatedAt()
        );
    }
}
