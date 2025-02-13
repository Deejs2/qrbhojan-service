package com.menu.qrbhojan.cafe.controller;

import com.menu.qrbhojan.cafe.dto.CafeRequest;
import com.menu.qrbhojan.cafe.service.CafeService;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cafe")
@RequiredArgsConstructor
public class CafeController extends BaseController {
    private final CafeService cafeService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createCafe(@ModelAttribute CafeRequest cafeRequest) throws IOException {
        return successResponse(cafeService.createCafe(cafeRequest), SystemMessage.CAFE_CREATED);
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<GlobalApiResponse> getCafe(@PathVariable Long cafeId) {
        return successResponse(cafeService.getCafe(cafeId), SystemMessage.CAFE_FETCHED);
    }

    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllCafes(Pageable pageable) {
        return successResponse(cafeService.getAllCafes(pageable), SystemMessage.CAFE_FETCHED);
    }

    @PutMapping("/update/{cafeId}")
    public ResponseEntity<GlobalApiResponse> updateCafe(@PathVariable Long cafeId, @ModelAttribute CafeRequest cafeRequest) throws IOException {
        return successResponse(cafeService.updateCafe(cafeId, cafeRequest), SystemMessage.CAFE_UPDATED);
    }

    @DeleteMapping("/delete/{cafeId}")
    public ResponseEntity<GlobalApiResponse> deleteCafe(@PathVariable Long cafeId) {
        cafeService.deleteCafe(cafeId);
        return successResponse(SystemMessage.CAFE_DELETED);
    }

    @GetMapping("/getCafeDetail")
    public ResponseEntity<GlobalApiResponse> getCafeBySpecialId() {
        return successResponse(cafeService.getCafeBySpecialId(), SystemMessage.CAFE_FETCHED);
    }
}
