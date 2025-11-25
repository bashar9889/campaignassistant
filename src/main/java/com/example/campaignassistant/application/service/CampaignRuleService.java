package com.example.campaignassistant.application.service;

import com.example.campaignassistant.application.mapper.CampaignRuleMapper;
import com.example.campaignassistant.domain.enums.CampaignChannel;
import com.example.campaignassistant.domain.enums.RuleTriggerType;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.infrastructure.persistence.entity.BusinessEntity;
import com.example.campaignassistant.infrastructure.persistence.entity.CampaignRuleEntity;
import com.example.campaignassistant.infrastructure.persistence.repository.CampaignRuleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignRuleService {

    private final CampaignRuleRepository campaignRuleRepository;
    private final BusinessService businessService;
    private final CampaignRuleMapper campaignRuleMapper;

    public CampaignRuleService(CampaignRuleRepository campaignRuleRepository,
                               BusinessService businessService,
                               CampaignRuleMapper campaignRuleMapper) {
        this.campaignRuleRepository = campaignRuleRepository;
        this.businessService = businessService;
        this.campaignRuleMapper = campaignRuleMapper;
    }

    public List<CampaignRule> createDefaultRulesForBusiness(Long businessId) {
        BusinessEntity businessEntity = businessService.getBusinessEntity(businessId);

        Instant now = Instant.now();
        List<CampaignRuleEntity> entities = new ArrayList<>();

        // Welcome rule
        CampaignRuleEntity welcome = new CampaignRuleEntity();
        welcome.setBusiness(businessEntity);
        welcome.setName("Welcome new customer");
        welcome.setTriggerType(RuleTriggerType.NEW_CUSTOMER);
        welcome.setTriggerValue(0);
        welcome.setChannel(CampaignChannel.EMAIL);
        welcome.setActive(true);
        welcome.setCreatedAt(now);
        entities.add(welcome);

        // 14-day win-back
        CampaignRuleEntity winback14 = new CampaignRuleEntity();
        winback14.setBusiness(businessEntity);
        winback14.setName("14-day win-back");
        winback14.setTriggerType(RuleTriggerType.INACTIVE_DAYS);
        winback14.setTriggerValue(14);
        winback14.setChannel(CampaignChannel.EMAIL);
        winback14.setActive(true);
        winback14.setCreatedAt(now);
        entities.add(winback14);

        // 30-day win-back
        CampaignRuleEntity winback30 = new CampaignRuleEntity();
        winback30.setBusiness(businessEntity);
        winback30.setName("30-day win-back");
        winback30.setTriggerType(RuleTriggerType.INACTIVE_DAYS);
        winback30.setTriggerValue(30);
        winback30.setChannel(CampaignChannel.EMAIL);
        winback30.setActive(true);
        winback30.setCreatedAt(now);
        entities.add(winback30);

        List<CampaignRuleEntity> saved = campaignRuleRepository.saveAll(entities);

        return saved.stream()
                .map(campaignRuleMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<CampaignRule> listRulesForBusiness(Long businessId) {
        return campaignRuleRepository.findByBusiness_Id(businessId)
                .stream()
                .map(campaignRuleMapper::toDomain)
                .collect(Collectors.toList());
    }
}
