package com.example.campaignassistant.web.controller;

import com.example.campaignassistant.application.command.CreateBusinessCommand;
import com.example.campaignassistant.application.service.BusinessService;
import com.example.campaignassistant.domain.enums.ToneOfVoice;
import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.web.dto.BusinessResponse;
import com.example.campaignassistant.web.dto.CreateBusinessRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessResponse createBusiness(@Valid @RequestBody CreateBusinessRequest request) {
        ToneOfVoice tone = ToneOfVoice.valueOf(request.getToneOfVoice().toUpperCase(Locale.ROOT));

        CreateBusinessCommand cmd = new CreateBusinessCommand(
                request.getName(),
                request.getNiche(),
                request.getTargetAudience(),
                tone,
                request.getLocation()
        );

        Business business = businessService.createBusiness(cmd);
        return toResponse(business);
    }

    @GetMapping
    public List<BusinessResponse> listBusinesses() {
        return businessService.listBusinesses()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{businessId}")
    public BusinessResponse getBusiness(@PathVariable Long businessId) {
        Business business = businessService.getBusiness(businessId);
        return toResponse(business);
    }

    private BusinessResponse toResponse(Business business) {
        return new BusinessResponse(
                business.getId(),
                business.getName(),
                business.getNiche(),
                business.getTargetAudience(),
                business.getToneOfVoice().name(),
                business.getLocation(),
                business.getCreatedAt()
        );
    }
}
