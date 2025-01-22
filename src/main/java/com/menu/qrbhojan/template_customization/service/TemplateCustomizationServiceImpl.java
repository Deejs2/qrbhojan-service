package com.menu.qrbhojan.template_customization.service;

import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationRequest;
import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationResponse;
import com.menu.qrbhojan.template_customization.entity.TemplateCustomization;
import com.menu.qrbhojan.template_customization.repository.TemplateCustomizationRepository;
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

    @Override
    public TemplateCustomizationResponse saveTemplateCustomization(TemplateCustomizationRequest templateCustomizationRequest) {
        TemplateCustomization templateCustomization = TemplateCustomization.builder()
                .customStyles(templateCustomizationRequest.getCustomStyles())
                .cafeSpecialId(null)
                .build();
        return new TemplateCustomizationResponse(templateCustomizationRepository.save(templateCustomization));
    }

    @Override
    public TemplateCustomizationResponse getTemplateCustomization() {
        return new TemplateCustomizationResponse(templateCustomizationRepository.findByCafeSpecialId(null)
                .orElseThrow(()-> new EntityNotFoundException("Template customization not found")));
    }
}
