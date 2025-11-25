package com.example.campaignassistant.application.service;

import com.example.campaignassistant.application.command.CreateBusinessCommand;
import com.example.campaignassistant.application.mapper.BusinessMapper;
import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.infrastructure.persistence.entity.BusinessEntity;
import com.example.campaignassistant.infrastructure.persistence.repository.BusinessRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public BusinessService(BusinessRepository businessRepository,
                           BusinessMapper businessMapper) {
        this.businessRepository = businessRepository;
        this.businessMapper = businessMapper;
    }

    public Business createBusiness(CreateBusinessCommand command) {
        BusinessEntity entity = new BusinessEntity();
        entity.setName(command.getName());
        entity.setNiche(command.getNiche());
        entity.setTargetAudience(command.getTargetAudience());
        entity.setToneOfVoice(command.getToneOfVoice());
        entity.setLocation(command.getLocation());
        entity.setCreatedAt(Instant.now());

        BusinessEntity saved = businessRepository.save(entity);
        return businessMapper.toDomain(saved);
    }

    public List<Business> listBusinesses() {
        return businessRepository.findAll()
                .stream()
                .map(businessMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Business getBusiness(Long id) {
        BusinessEntity entity = businessRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business not found"));
        return businessMapper.toDomain(entity);
    }

    public BusinessEntity getBusinessEntity(Long id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business not found"));
    }
}
