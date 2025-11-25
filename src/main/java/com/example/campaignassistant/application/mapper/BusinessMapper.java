package com.example.campaignassistant.application.mapper;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.infrastructure.persistence.entity.BusinessEntity;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public Business toDomain(BusinessEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Business(
                entity.getId(),
                entity.getName(),
                entity.getNiche(),
                entity.getTargetAudience(),
                entity.getToneOfVoice(),
                entity.getLocation(),
                entity.getCreatedAt()
        );
    }
}
