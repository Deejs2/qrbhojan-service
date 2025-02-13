package com.menu.qrbhojan.cafe.service;

import com.menu.qrbhojan.cafe.dto.CafeRequest;
import com.menu.qrbhojan.cafe.dto.CafeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface CafeService {
    CafeResponse createCafe(CafeRequest cafeRequest) throws IOException;
    CafeResponse getCafe(Long cafeId);
    Page<CafeResponse> getAllCafes(Pageable pageable);
    CafeResponse updateCafe(Long cafeId, CafeRequest cafeRequest) throws IOException;
    void deleteCafe(Long cafeId);
    CafeResponse getCafeBySpecialId();
}
