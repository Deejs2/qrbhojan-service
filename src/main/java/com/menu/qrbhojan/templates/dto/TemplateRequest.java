package com.menu.qrbhojan.templates.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequest {

    private String name;

    private String description;

    private String defaultStyles;

    private MultipartFile previewImage;
}
