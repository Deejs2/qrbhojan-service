package com.menu.qrbhojan.customer_support.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.customer_support.dto.CustomerSupportRequest;
import com.menu.qrbhojan.customer_support.entity.SupportStatus;
import com.menu.qrbhojan.customer_support.service.CustomerSupportService;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/customer-support")
@RequiredArgsConstructor
public class CustomerSupportController extends BaseController {
    private final CustomerSupportService customerSupportService;
    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createSupportRequest(@ModelAttribute CustomerSupportRequest customerSupportRequest) throws IOException {
        return successResponse(customerSupportService.createSupportRequest(customerSupportRequest), SystemMessage.SUPPORT_REQUEST_CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllSupportRequests(Pageable pageable, @RequestParam(defaultValue = "OPEN") SupportStatus supportStatus) {
        return successResponse(customerSupportService.getAllSupportRequests(pageable, supportStatus), SystemMessage.SUPPORT_REQUEST_FETCHED);
    }
    @GetMapping("/{supportRequestId}")
    public ResponseEntity<GlobalApiResponse> getSupportRequest(@PathVariable Long supportRequestId) {
        return successResponse(customerSupportService.getSupportRequest(supportRequestId), SystemMessage.SUPPORT_REQUEST_FETCHED);
    }
    @PutMapping("/{supportRequestId}")
    public ResponseEntity<GlobalApiResponse> updateSupportRequest(@PathVariable Long supportRequestId, @ModelAttribute CustomerSupportRequest customerSupportRequest) throws IOException {
        return successResponse(customerSupportService.updateSupportRequest(supportRequestId, customerSupportRequest), SystemMessage.SUPPORT_REQUEST_UPDATED);
    }
    @DeleteMapping("/{supportRequestId}")
    public ResponseEntity<GlobalApiResponse> deleteSupportRequest(@PathVariable Long supportRequestId) {
        customerSupportService.deleteSupportRequest(supportRequestId);
        return successResponse(SystemMessage.SUPPORT_REQUEST_DELETED);
    }
    @GetMapping("/get")
    public ResponseEntity<GlobalApiResponse> getSupportRequestByUser(Pageable pageable, @RequestParam(defaultValue = "OPEN") SupportStatus supportStatus) {
        return successResponse(customerSupportService.getSupportRequestByUser(pageable, supportStatus), SystemMessage.SUPPORT_REQUEST_FETCHED);
    }
    @PutMapping("/status/{supportRequestId}")
    public ResponseEntity<GlobalApiResponse> updateSupportStatus(@PathVariable Long supportRequestId, @RequestParam SupportStatus supportStatus) {
        return successResponse(customerSupportService.updateSupportStatus(supportRequestId, supportStatus), SystemMessage.SUPPORT_REQUEST_STATUS_UPDATED);
    }
}
