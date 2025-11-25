package com.example.campaignassistant.infrastructure.persistence.repository;

import com.example.campaignassistant.infrastructure.persistence.entity.CampaignRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRuleRepository extends JpaRepository<CampaignRuleEntity, Long> {

    List<CampaignRuleEntity> findByBusiness_Id(Long businessId);

    List<CampaignRuleEntity> findByBusiness_IdAndActiveTrue(Long businessId);
}
