package com.menu.qrbhojan.website.service;

import com.google.zxing.WriterException;
import com.menu.qrbhojan.website.dto.CafeWebsiteRequest;
import com.menu.qrbhojan.website.dto.CafeWebsiteResponse;
import com.menu.qrbhojan.website.entity.WebsiteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface CafeWebsiteService {
    CafeWebsiteResponse createCafeWebsite(CafeWebsiteRequest cafeWebsiteRequest);
    CafeWebsiteResponse getCafeWebsite(Long cafeWebsiteId);
    Page<CafeWebsiteResponse> getAllCafeWebsites(Pageable pageable, WebsiteStatus cafeWebsiteStatus);
    CafeWebsiteResponse updateCafeWebsite(Long cafeWebsiteId, CafeWebsiteRequest cafeWebsiteRequest);
    void deleteCafeWebsite(Long cafeWebsiteId);
    CafeWebsiteResponse getCafeWebsite();
}
