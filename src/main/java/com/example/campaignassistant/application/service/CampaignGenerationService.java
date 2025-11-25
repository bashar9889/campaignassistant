package com.example.campaignassistant.application.service;

import com.example.campaignassistant.application.mapper.GeneratedCampaignMapper;
import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.domain.model.GeneratedCampaign;
import com.example.campaignassistant.infrastructure.ai.CampaignCopyGenerator;
import com.example.campaignassistant.infrastructure.ai.GeneratedCampaignDraft;
import com.example.campaignassistant.infrastructure.persistence.entity.BusinessEntity;
import com.example.campaignassistant.infrastructure.persistence.entity.CampaignRuleEntity;
import com.example.campaignassistant.infrastructure.persistence.entity.GeneratedCampaignEntity;
import com.example.campaignassistant.infrastructure.persistence.repository.CampaignRuleRepository;
import com.example.campaignassistant.infrastructure.persistence.repository.GeneratedCampaignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignGenerationService {

    private final BusinessService businessService;
    private final CampaignRuleRepository campaignRuleRepository;
    private final GeneratedCampaignRepository generatedCampaignRepository;
    private final CampaignCopyGenerator campaignCopyGenerator;
    private final GeneratedCampaignMapper generatedCampaignMapper;

    public CampaignGenerationService(BusinessService businessService,
                                     CampaignRuleRepository campaignRuleRepository,
                                     GeneratedCampaignRepository generatedCampaignRepository,
                                     CampaignCopyGenerator campaignCopyGenerator,
                                     GeneratedCampaignMapper generatedCampaignMapper) {
        this.businessService = businessService;
        this.campaignRuleRepository = campaignRuleRepository;
        this.generatedCampaignRepository = generatedCampaignRepository;
        this.campaignCopyGenerator = campaignCopyGenerator;
        this.generatedCampaignMapper = generatedCampaignMapper;
    }

    public List<GeneratedCampaign> generateCampaignsForBusiness(Long businessId) {
        BusinessEntity businessEntity = businessService.getBusinessEntity(businessId);
        Business businessDomain = new Business(
                businessEntity.getId(),
                businessEntity.getName(),
                businessEntity.getNiche(),
                businessEntity.getTargetAudience(),
                businessEntity.getToneOfVoice(),
                businessEntity.getLocation(),
                businessEntity.getCreatedAt()
        );

        List<CampaignRuleEntity> activeRules =
                campaignRuleRepository.findByBusiness_IdAndActiveTrue(businessId);

        List<GeneratedCampaignEntity> toSave = activeRules.stream()
                .map(ruleEntity -> {
                    CampaignRule ruleDomain = new CampaignRule(
                            ruleEntity.getId(),
                            businessDomain,
                            ruleEntity.getName(),
                            ruleEntity.getTriggerType(),
                            ruleEntity.getTriggerValue(),
                            ruleEntity.getChannel(),
                            ruleEntity.isActive(),
                            ruleEntity.getCreatedAt()
                    );

                    GeneratedCampaignDraft draft =
                            campaignCopyGenerator.generateCopy(businessDomain, ruleDomain);

                    GeneratedCampaignEntity gce = new GeneratedCampaignEntity();
                    gce.setBusiness(businessEntity);
                    gce.setCampaignRule(ruleEntity);
                    gce.setSubject(draft.getSubject());
                    gce.setBody(draft.getBody());
                    gce.setGeneratedAt(Instant.now());
                    gce.setModelInfo("mock-ai");
                    return gce;
                })
                .collect(Collectors.toList());

        List<GeneratedCampaignEntity> saved = generatedCampaignRepository.saveAll(toSave);

        return saved.stream()
                .map(generatedCampaignMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<GeneratedCampaign> listGeneratedCampaignsForBusiness(Long businessId) {
        List<GeneratedCampaignEntity> entities =
                generatedCampaignRepository.findByBusiness_Id(businessId);
        return entities.stream()
                .map(generatedCampaignMapper::toDomain)
                .collect(Collectors.toList());
    }

    public GeneratedCampaign getCampaignById(Long campaignId) {
        GeneratedCampaignEntity entity = generatedCampaignRepository.findById(campaignId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Generated campaign not found"));
        return generatedCampaignMapper.toDomain(entity);
    }
}
