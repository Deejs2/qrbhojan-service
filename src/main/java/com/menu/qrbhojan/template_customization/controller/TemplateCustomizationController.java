package com.menu.qrbhojan.template_customization.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.template_customization.dto.TemplateCustomizationRequest;
import com.menu.qrbhojan.template_customization.service.TemplateCustomizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template-customization")
public class TemplateCustomizationController extends BaseController {
    private final TemplateCustomizationService templateCustomizationService;

//    @PostMapping("/create")
//    public ResponseEntity<GlobalApiResponse> createUser(@ModelAttribute TemplateCustomizationRequest userRequest) throws IOException {
//        return successResponse(templateCustomizationService.createUser(userRequest), SystemMessage.USER_CREATED);
//    }

}
