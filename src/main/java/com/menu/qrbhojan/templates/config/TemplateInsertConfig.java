package com.menu.qrbhojan.templates.config;

import com.menu.qrbhojan.templates.entity.Template;
import com.menu.qrbhojan.templates.repository.TemplateRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class TemplateInsertConfig {

    private final TemplateRepository templateRepository;

    @PostConstruct
    public void insertTemplates() {
        log.info("Inserting templates");
        createTemplateIfNotExists("Modern", "Clean and minimalist design with focus on visuals", "assets/templates/modern.png");
        createTemplateIfNotExists("Elegant", "Sophisticated design with classic elements", "assets/templates/elegant.png");
        createTemplateIfNotExists("Rustic", "Warm and inviting farm-to-table style", "assets/templates/rustic.png");
    }

    private void createTemplateIfNotExists(String name, String description, String imagePath) {
        if(templateRepository.findByName(name).isEmpty()) {
            log.info("Creating template: {}", name);
            Template template = new Template();
            template.setName(name);
            template.setDescription(description);
            template.setPreviewImage(imagePath);
            templateRepository.save(template);
            log.info("Template {} created", name);
        } else {
            log.info("Template {} already exists", name);
        }
    }
}
