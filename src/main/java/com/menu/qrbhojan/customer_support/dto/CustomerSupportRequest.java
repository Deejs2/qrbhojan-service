package com.menu.qrbhojan.customer_support.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupportRequest {
    private String subject;
    private String description;
    private List<MultipartFile> images;
}
