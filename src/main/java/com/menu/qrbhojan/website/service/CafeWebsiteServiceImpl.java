package com.menu.qrbhojan.website.service;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.utils.LoggedInUser;
import com.menu.qrbhojan.utils.QrCodeGenerator;
import com.menu.qrbhojan.website.dto.CafeWebsiteRequest;
import com.menu.qrbhojan.website.dto.CafeWebsiteResponse;
import com.menu.qrbhojan.website.entity.CafeWebsite;
import com.menu.qrbhojan.website.entity.WebsiteStatus;
import com.menu.qrbhojan.website.repository.CafeWebsiteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeWebsiteServiceImpl implements CafeWebsiteService {
    private final CafeWebsiteRepository cafeWebsiteRepository;
    private final LoggedInUser loggedInUser;
    private final QrCodeGenerator qrCodeGenerator;

    @Override
    public CafeWebsiteResponse createCafeWebsite(CafeWebsiteRequest cafeWebsiteRequest) {
        log.info("Creating Cafe Website with Details: {}", cafeWebsiteRequest);
        CafeWebsite cafeWebsite = buildCafeWebsiteFromRequest(cafeWebsiteRequest, new CafeWebsite());
        cafeWebsiteRepository.save(cafeWebsite);
        return new CafeWebsiteResponse(cafeWebsite);
    }

    @Override
    public CafeWebsiteResponse getCafeWebsite(Long cafeWebsiteId) {
        log.info("Getting Cafe Website with ID: {}", cafeWebsiteId);
        CafeWebsite cafeWebsite = cafeWebsiteRepository.findById(cafeWebsiteId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.CAFE_WEBSITE_NOT_FOUND));
        return new CafeWebsiteResponse(cafeWebsite);
    }

    @Override
    public Page<CafeWebsiteResponse> getAllCafeWebsites(Pageable pageable, WebsiteStatus cafeWebsiteStatus) {
        log.info("Getting All Cafe Websites");
        Page<CafeWebsite> cafeWebsites = cafeWebsiteRepository.findAllByCafeWebsiteStatus(pageable, cafeWebsiteStatus).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.CAFE_WEBSITE_NOT_FOUND)
        );
        return cafeWebsites.map(CafeWebsiteResponse::new);
    }

    @Override
    public CafeWebsiteResponse updateCafeWebsite(Long cafeWebsiteId, CafeWebsiteRequest cafeWebsiteRequest) {
        log.info("Updating Cafe Website with ID: {} and Details: {}", cafeWebsiteId, cafeWebsiteRequest);
        CafeWebsite cafeWebsite = cafeWebsiteRepository.findById(cafeWebsiteId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.CAFE_WEBSITE_NOT_FOUND));
        buildCafeWebsiteFromRequest(cafeWebsiteRequest, cafeWebsite);
        cafeWebsiteRepository.save(cafeWebsite);
        return new CafeWebsiteResponse(cafeWebsite);
    }

    @Override
    public void deleteCafeWebsite(Long cafeWebsiteId) {
        log.info("Deleting Cafe Website with ID: {}", cafeWebsiteId);
        CafeWebsite cafeWebsite = cafeWebsiteRepository.findById(cafeWebsiteId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.CAFE_WEBSITE_NOT_FOUND));
        cafeWebsiteRepository.delete(cafeWebsite);
    }

    @Override
    public CafeWebsiteResponse getCafeWebsite() {
        return cafeWebsiteRepository.findByCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId())
                .map(CafeWebsiteResponse::new)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.CAFE_WEBSITE_NOT_FOUND)
                );
    }

    private CafeWebsite buildCafeWebsiteFromRequest(CafeWebsiteRequest cafeWebsiteRequest, CafeWebsite cafeWebsite) {
        cafeWebsite.setCafeWebsiteUrl(cafeWebsiteRequest.getCafeWebsiteUrl());
        cafeWebsite.setCafeWebsiteQrCode(qrCodeGenerator.generateQRCodeImage(cafeWebsiteRequest.getCafeWebsiteUrl(), 300, 300));
        cafeWebsite.setCafeWebsiteStatus(WebsiteStatus.valueOf(cafeWebsiteRequest.getCafeWebsiteStatus()));
        cafeWebsite.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        return cafeWebsite;
    }
}