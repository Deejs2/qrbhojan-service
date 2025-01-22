package com.menu.qrbhojan.template_customization.service;

import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationRequest;
import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationResponse;

public interface TemplateCustomizationService {

    TemplateCustomizationResponse saveTemplateCustomization(TemplateCustomizationRequest templateCustomizationRequest);

    TemplateCustomizationResponse getTemplateCustomization();
}
