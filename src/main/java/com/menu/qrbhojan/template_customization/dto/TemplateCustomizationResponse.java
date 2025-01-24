package com.menu.qrbhojan.template_customization.dto;

import com.menu.qrbhojan.template_customization.entity.TemplateCustomization;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TemplateCustomizationResponse {
    private Long id;
    private String customStyles;

    public TemplateCustomizationResponse(TemplateCustomization user) {
        this.id = user.getId();
        this.customStyles = user.getCustomStyles();
    }
}
