package com.menu.qrbhojan.customer_support.service;

import com.menu.qrbhojan.customer_support.dto.CustomerSupportRequest;
import com.menu.qrbhojan.customer_support.dto.CustomerSupportResponse;
import com.menu.qrbhojan.customer_support.entity.SupportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface CustomerSupportService {
    CustomerSupportResponse createSupportRequest(CustomerSupportRequest customerSupportRequest) throws IOException;
    Page<CustomerSupportResponse> getAllSupportRequests(Pageable pageable, SupportStatus supportStatus);
    CustomerSupportResponse getSupportRequest(Long id);
    CustomerSupportResponse updateSupportRequest(Long id, CustomerSupportRequest customerSupportRequest) throws IOException;
    void deleteSupportRequest(Long id);
    Page<CustomerSupportResponse> getSupportRequestByUser(Pageable pageable, SupportStatus supportStatus);
    CustomerSupportResponse updateSupportStatus(Long id, SupportStatus supportStatus);
}
