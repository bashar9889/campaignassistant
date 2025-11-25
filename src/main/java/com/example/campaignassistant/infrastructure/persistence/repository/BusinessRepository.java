package com.example.campaignassistant.infrastructure.persistence.repository;

import com.example.campaignassistant.infrastructure.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {
}
