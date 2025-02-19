package com.menu.qrbhojan.customer_support.dto;

import com.menu.qrbhojan.customer_support.entity.CustomerSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupportResponse {
    private String customerSupportId;
    private String subject;
    private String description;
    private List<String> images;
    private String status;
    private Long userId;

    public CustomerSupportResponse(CustomerSupport customerSupport) {
        this.customerSupportId = customerSupport.getId().toString();
        this.subject = customerSupport.getSubject();
        this.description = customerSupport.getDescription();
        this.images = customerSupport.getImages();
        this.status = customerSupport.getStatus().name();
        this.userId = customerSupport.getUserId();
    }
}
