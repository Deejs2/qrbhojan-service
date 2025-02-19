package com.menu.qrbhojan.customer_support.service;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.customer_support.dto.CustomerSupportRequest;
import com.menu.qrbhojan.customer_support.dto.CustomerSupportResponse;
import com.menu.qrbhojan.customer_support.entity.CustomerSupport;
import com.menu.qrbhojan.customer_support.entity.SupportStatus;
import com.menu.qrbhojan.customer_support.repository.CustomerSupportRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSupportServiceImpl implements CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;
    private final LoggedInUser loggedInUser;
    private final FileHandler fileHandler;

    @Override
    public CustomerSupportResponse createSupportRequest(CustomerSupportRequest customerSupportRequest) throws IOException {
        CustomerSupport customerSupport = buildCustomerSupportFromRequest(customerSupportRequest, new CustomerSupport());
        customerSupport.setStatus(SupportStatus.OPEN);
        customerSupport.setUserId(loggedInUser.getLoggedInUser().getId());
        customerSupportRepository.save(customerSupport);
        return new CustomerSupportResponse(customerSupport);
    }

    @Override
    public CustomerSupportResponse updateSupportRequest(Long id, CustomerSupportRequest customerSupportRequest) throws IOException {
        log.info("Updating support request by id: {}", id);
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        buildCustomerSupportFromRequest(customerSupportRequest, customerSupport);
        customerSupportRepository.save(customerSupport);
        return new CustomerSupportResponse(customerSupport);
    }

    private CustomerSupport buildCustomerSupportFromRequest(CustomerSupportRequest customerSupportRequest, CustomerSupport customerSupport) throws IOException {
        customerSupport.setSubject(customerSupportRequest.getSubject());
        customerSupport.setDescription(customerSupportRequest.getDescription());

        List<String> imageUrls = new ArrayList<>();
        if (customerSupportRequest.getImages() != null) {
            for (MultipartFile file : customerSupportRequest.getImages()) {
                String filePath = fileHandler.saveMediaFile(file, "customer_support").getFilePath();
                imageUrls.add(filePath);
            }
        }
        customerSupport.setImages(imageUrls);  // Store as a JSON array
        return customerSupport;
    }

    @Override
    public Page<CustomerSupportResponse> getAllSupportRequests(Pageable pageable, SupportStatus supportStatus) {
        log.info("Fetching all support requests");
        Page<CustomerSupport> customerSupports = customerSupportRepository.findAllByStatus(pageable, supportStatus).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        return customerSupports.map(CustomerSupportResponse::new);
    }

    @Override
    public CustomerSupportResponse getSupportRequest(Long id) {
        log.info("Fetching support request by id: {}", id);
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        return new CustomerSupportResponse(customerSupport);
    }

    @Override
    public void deleteSupportRequest(Long id) {
        log.info("Deleting support request by id: {}", id);
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        customerSupportRepository.delete(customerSupport);
    }

    @Override
    public Page<CustomerSupportResponse> getSupportRequestByUser(Pageable pageable, SupportStatus supportStatus) {
        log.info("Fetching support request by user");
        Page<CustomerSupport> customerSupports = customerSupportRepository.findAllByUserIdAndStatus(pageable, loggedInUser.getLoggedInUser().getId(), supportStatus).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        return customerSupports.map(CustomerSupportResponse::new);
    }

    @Override
    public CustomerSupportResponse updateSupportStatus(Long id, SupportStatus supportStatus) {
        log.info("Updating support request status by id: {}", id);
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(
                () -> new RuntimeException(SystemMessage.SUPPORT_REQUEST_NOT_FOUND)
        );
        customerSupport.setStatus(supportStatus);
        customerSupportRepository.save(customerSupport);
        return new CustomerSupportResponse(customerSupport);
    }
}
