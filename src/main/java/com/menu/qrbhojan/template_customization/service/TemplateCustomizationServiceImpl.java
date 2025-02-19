package com.menu.qrbhojan.template_customization.service;

import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationRequest;
import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationResponse;
import com.menu.qrbhojan.template_customization.entity.TemplateCustomization;
import com.menu.qrbhojan.template_customization.repository.TemplateCustomizationRepository;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateCustomizationServiceImpl implements TemplateCustomizationService {
    private final TemplateCustomizationRepository templateCustomizationRepository;
    private final LoggedInUser loggedInUser;

    public TemplateCustomizationResponse saveTemplateCustomization(TemplateCustomizationRequest templateCustomizationRequest) {
        Users user = loggedInUser.getLoggedInUser();
        if (user.getCafe() == null) {
            throw new RuntimeException("Cafe not found. Please create your cafe first");
        }

        TemplateCustomization templateCustomization = templateCustomizationRepository.findByCafeSpecialId(user.getCafe().getCafeSpecialId())
                .map(existingTemplate -> {
                    existingTemplate.setCustomStyles(templateCustomizationRequest.getCustomStyles());
                    return existingTemplate;
                })
                .orElse(TemplateCustomization.builder()
                        .customStyles(templateCustomizationRequest.getCustomStyles())
                        .cafeSpecialId(user.getCafe().getCafeSpecialId())
                        .build());

        return new TemplateCustomizationResponse(templateCustomizationRepository.save(templateCustomization));
    }

    @Override
    public TemplateCustomizationResponse getTemplateCustomization() {
        return new TemplateCustomizationResponse(templateCustomizationRepository.findByCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId())
                .orElseThrow(() -> new EntityNotFoundException("Template customization not found")));
    }
}