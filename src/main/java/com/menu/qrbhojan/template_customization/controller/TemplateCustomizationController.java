package com.menu.qrbhojan.template_customization.controller;

import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationRequest;
import com.menu.qrbhojan.template_customization.message.TemplateCustomizationMessage;
import com.menu.qrbhojan.template_customization.service.TemplateCustomizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template-customization")
public class TemplateCustomizationController extends BaseController {
    private final TemplateCustomizationService templateCustomizationService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createUser(@RequestBody TemplateCustomizationRequest templateCustomizationRequest) {
        return successResponse(templateCustomizationService.saveTemplateCustomization(templateCustomizationRequest), TemplateCustomizationMessage.TEMPLATE_CUSTOMIZATION_CREATED, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<GlobalApiResponse> getTemplateCustomization() {
        return successResponse(templateCustomizationService.getTemplateCustomization(), TemplateCustomizationMessage.TEMPLATE_CUSTOMIZATION_NOT_FOUND, HttpStatus.OK);
    }
}
