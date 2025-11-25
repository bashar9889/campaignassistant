package com.example.campaignassistant.web.controller;

import com.example.campaignassistant.application.service.CampaignGenerationService;
import com.example.campaignassistant.domain.model.GeneratedCampaign;
import com.example.campaignassistant.web.dto.GeneratedCampaignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CampaignGenerationController {

    private final CampaignGenerationService campaignGenerationService;

    public CampaignGenerationController(CampaignGenerationService campaignGenerationService) {
        this.campaignGenerationService = campaignGenerationService;
    }

    @PostMapping("/businesses/{businessId}/campaigns/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public List<GeneratedCampaignResponse> generateCampaigns(@PathVariable Long businessId) {
        List<GeneratedCampaign> campaigns =
                campaignGenerationService.generateCampaignsForBusiness(businessId);
        return campaigns.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/businesses/{businessId}/campaigns")
    public List<GeneratedCampaignResponse> listCampaigns(@PathVariable Long businessId) {
        List<GeneratedCampaign> campaigns =
                campaignGenerationService.listGeneratedCampaignsForBusiness(businessId);
        return campaigns.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/campaigns/{campaignId}")
    public GeneratedCampaignResponse getCampaign(@PathVariable Long campaignId) {
        GeneratedCampaign campaign = campaignGenerationService.getCampaignById(campaignId);
        return toResponse(campaign);
    }

    private GeneratedCampaignResponse toResponse(GeneratedCampaign campaign) {
        return new GeneratedCampaignResponse(
                campaign.getId(),
                campaign.getBusiness().getId(),
                campaign.getCampaignRule().getId(),
                campaign.getSubject(),
                campaign.getBody(),
                campaign.getGeneratedAt()
        );
    }
}
