package com.example.campaignassistant.web.controller;

import com.example.campaignassistant.application.service.CampaignRuleService;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.web.dto.CampaignRuleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/businesses/{businessId}/rules")
public class CampaignRuleController {

    private final CampaignRuleService campaignRuleService;

    public CampaignRuleController(CampaignRuleService campaignRuleService) {
        this.campaignRuleService = campaignRuleService;
    }

    @PostMapping("/default")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CampaignRuleResponse> createDefaultRules(@PathVariable Long businessId) {
        List<CampaignRule> rules = campaignRuleService.createDefaultRulesForBusiness(businessId);
        return rules.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<CampaignRuleResponse> listRules(@PathVariable Long businessId) {
        List<CampaignRule> rules = campaignRuleService.listRulesForBusiness(businessId);
        return rules.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CampaignRuleResponse toResponse(CampaignRule rule) {
        return new CampaignRuleResponse(
                rule.getId(),
                rule.getName(),
                rule.getTriggerType().name(),
                rule.getTriggerValue(),
                rule.getChannel().name(),
                rule.isActive()
        );
    }
}
