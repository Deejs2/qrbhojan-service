package com.menu.qrbhojan.templates.service;

import com.menu.qrbhojan.templates.dto.TemplateRequest;
import com.menu.qrbhojan.templates.dto.TemplateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateService {

    TemplateResponse saveTemplate(TemplateRequest templateRequest);

    Page<TemplateResponse> getTemplates(Pageable pageable);

    TemplateResponse getTemplate(Long id);
}
