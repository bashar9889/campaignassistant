package com.example.campaignassistant.infrastructure.persistence.repository;

import com.example.campaignassistant.infrastructure.persistence.entity.GeneratedCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneratedCampaignRepository extends JpaRepository<GeneratedCampaignEntity, Long> {

    List<GeneratedCampaignEntity> findByBusiness_Id(Long businessId);
}
