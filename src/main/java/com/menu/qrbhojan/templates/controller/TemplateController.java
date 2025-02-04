package com.menu.qrbhojan.templates.controller;

import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.templates.dto.TemplateRequest;
import com.menu.qrbhojan.templates.message.TemplateMessage;
import com.menu.qrbhojan.templates.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
public class TemplateController extends BaseController {
    private final TemplateService templateService;

    @PostMapping("/save")
    public ResponseEntity<GlobalApiResponse> saveTemplate(@ModelAttribute TemplateRequest templateRequest){
        return successResponse(templateService.saveTemplate(templateRequest), TemplateMessage.TEMPLATE_SAVED, HttpStatus.CREATED);
    }

    @GetMapping("/getTemplates")
    public ResponseEntity<GlobalApiResponse> getTemplates(Pageable pageable){
        return successResponse(templateService.getTemplates(pageable), TemplateMessage.TEMPLATES_FETCHED);
    }

    @GetMapping("/getTemplate/{id}")
    public ResponseEntity<GlobalApiResponse> getTemplate(@PathVariable Long id){
        return successResponse(templateService.getTemplate(id), TemplateMessage.TEMPLATE_FETCHED);
    }
}
