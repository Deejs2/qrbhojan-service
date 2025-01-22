package com.menu.qrbhojan.template_customization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCustomizationRequest {
    private Long id;
    private String customStyles;
}
