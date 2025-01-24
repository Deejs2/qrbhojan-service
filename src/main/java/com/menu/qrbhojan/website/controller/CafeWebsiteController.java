package com.menu.qrbhojan.website.controller;

import com.google.zxing.WriterException;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.website.dto.CafeWebsiteRequest;
import com.menu.qrbhojan.website.entity.WebsiteStatus;
import com.menu.qrbhojan.website.service.CafeWebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cafe-website")
public class CafeWebsiteController extends BaseController {
    private final CafeWebsiteService cafeWebsiteService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createCafeWebsite(@RequestBody CafeWebsiteRequest cafeWebsiteRequest) {
        return successResponse(cafeWebsiteService.createCafeWebsite(cafeWebsiteRequest), SystemMessage.CAFE_WEBSITE_CREATED);
    }

    @GetMapping("/{cafeWebsiteId}")
    public ResponseEntity<GlobalApiResponse> getCafeWebsite(@PathVariable Long cafeWebsiteId) {
        return successResponse(cafeWebsiteService.getCafeWebsite(cafeWebsiteId), SystemMessage.CAFE_WEBSITE_FETCHED);
    }

    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllCafeWebsites(Pageable pageable, @RequestParam(defaultValue = "PUBLISHED") WebsiteStatus cafeWebsiteStatus) {
        return successResponse(cafeWebsiteService.getAllCafeWebsites(pageable, cafeWebsiteStatus), SystemMessage.CAFE_WEBSITE_FETCHED);
    }

    @PutMapping("/{cafeWebsiteId}")
    public ResponseEntity<GlobalApiResponse> updateCafeWebsite(@PathVariable Long cafeWebsiteId, @RequestBody CafeWebsiteRequest cafeWebsiteRequest) {
        return successResponse(cafeWebsiteService.updateCafeWebsite(cafeWebsiteId, cafeWebsiteRequest), SystemMessage.CAFE_WEBSITE_UPDATED);
    }

    @DeleteMapping("/{cafeWebsiteId}")
    public ResponseEntity<GlobalApiResponse> deleteCafeWebsite(@PathVariable Long cafeWebsiteId) {
        cafeWebsiteService.deleteCafeWebsite(cafeWebsiteId);
        return successResponse(SystemMessage.CAFE_WEBSITE_DELETED);
    }

    @GetMapping("/get")
    public ResponseEntity<GlobalApiResponse> getCafeWebsite() {
        return successResponse(cafeWebsiteService.getCafeWebsite(), SystemMessage.CAFE_WEBSITE_FETCHED);
    }

}
