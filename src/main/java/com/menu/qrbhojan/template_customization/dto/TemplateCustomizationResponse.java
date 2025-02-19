package com.menu.qrbhojan.template_customization.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.menu.qrbhojan.template_customization.entity.TemplateCustomization;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TemplateCustomizationResponse {
    private Long id;
    private Map<String, Object> customStyles;

    public TemplateCustomizationResponse(TemplateCustomization user) {
        this.id = user.getId();
        this.customStyles = convertToMap(user.getCustomStyles());
    }

    private Map<String, Object> convertToMap(String customStyles) {
        try {
            return new ObjectMapper().readValue(customStyles, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}