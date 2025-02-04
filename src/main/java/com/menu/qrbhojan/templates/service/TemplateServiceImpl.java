package com.menu.qrbhojan.templates.service;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.templates.dto.TemplateRequest;
import com.menu.qrbhojan.templates.dto.TemplateResponse;
import com.menu.qrbhojan.templates.entity.Template;
import com.menu.qrbhojan.templates.message.TemplateMessage;
import com.menu.qrbhojan.templates.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    private final FileHandler fileHandler;

    @Override
    public TemplateResponse saveTemplate(TemplateRequest templateRequest) {
        Template template = new Template();
        template.setName(templateRequest.getName());
        template.setDescription(templateRequest.getDescription());
        try {
            template.setPreviewImage(fileHandler.saveMediaFile(templateRequest.getPreviewImage(), "template").getFilePath());
        } catch (Exception e) {
            log.error("Error while saving preview image", e);
        }
        return new TemplateResponse(templateRepository.save(template));
    }

    @Override
    public Page<TemplateResponse> getTemplates(Pageable pageable) {
        return templateRepository.findAll(pageable).map(TemplateResponse::new);
    }

    @Override
    public TemplateResponse getTemplate(Long id) {
        Template template = templateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(TemplateMessage.TEMPLATE_NOT_FOUND));
        return new TemplateResponse(template);
    }

}
