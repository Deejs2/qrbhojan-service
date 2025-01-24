package com.menu.qrbhojan.templates.dto;

import com.menu.qrbhojan.templates.entity.Template;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TemplateResponse {
    private Long id;

    private String name;

    private String description;

    private String defaultStyles;

    private String previewImage;

    public TemplateResponse (Template template) {
        this.id = template.getId();
        this.name = template.getName();
        this.description = template.getDescription();
        this.defaultStyles = template.getDefaultStyles();
        this.previewImage = template.getPreviewImage();
    }
}
