package com.menu.qrbhojan.cafe.service;

import com.menu.qrbhojan.cafe.dto.CafeRequest;
import com.menu.qrbhojan.cafe.dto.CafeResponse;
import com.menu.qrbhojan.cafe.entity.Cafe;
import com.menu.qrbhojan.cafe.repository.CafeRepository;
import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import com.menu.qrbhojan.utils.CafeSpecialIdGenerator;
import com.menu.qrbhojan.utils.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService{
    private final CafeRepository cafeRepository;
    private final FileHandler fileHandler;
    private final LoggedInUser loggedInUser;
    private final CafeSpecialIdGenerator cafeSpecialIdGenerator;
    private final UserRepository userRepository;
    @Override
    public CafeResponse createCafe(CafeRequest cafeRequest) throws IOException {
        Users user = loggedInUser.getLoggedInUser();
        log.info("Creating cafe");
        Cafe cafe = new Cafe();
        cafe.setCafeName(cafeRequest.getCafeName());
        if (cafeRequest.getCafeLogo() != null) {
            cafe.setCafeLogo(fileHandler.saveMediaFile(cafeRequest.getCafeLogo(), "cafe").getFilePath());
        }
        cafe.setCafeLocation(cafeRequest.getCafeLocation());
        cafe.setCafeDescription(cafeRequest.getCafeDescription());
        if(cafeRequest.getCafeBanner() != null) {
            cafe.setCafeBanner(fileHandler.saveMediaFile(cafeRequest.getCafeBanner(), "cafe").getFilePath());
        }
        cafe.setCafeContact(cafeRequest.getCafeContact());
        cafe.setCafeEmail(cafeRequest.getCafeEmail());
        String cypherText = cafeSpecialIdGenerator.generateCafeSpecialId(cafeRequest.getCafeName(), cafe.getCafeId(),user.getFullName());
        cafe.setCafeSpecialId(cypherText);
        cafe.setCafeOpeningHours(cafeRequest.getCafeOpeningHours());
        cafe.setUserId(user.getId());
        cafeRepository.save(cafe);
        return new CafeResponse(cafe, user);
    }

    @Override
    public CafeResponse getCafe(Long cafeId) {
        log.info("Fetching cafe with id: {}", cafeId);
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new RuntimeException(SystemMessage.CAFE_NOT_FOUND));
        Users user = userRepository.findById(cafe.getUserId()).orElseThrow(() -> new RuntimeException(SystemMessage.USER_NOT_FOUND));
        return new CafeResponse(cafe, user);
    }

    @Override
    public Page<CafeResponse> getAllCafes(Pageable pageable) {
        Page<Cafe> cafes = cafeRepository.findAll(pageable);
        return cafes.map(cafe -> {
            Users user = userRepository.findById(cafe.getUserId()).orElseThrow(() -> new RuntimeException(SystemMessage.USER_NOT_FOUND));
            return new CafeResponse(cafe, user);
        });
    }

    @Override
    public CafeResponse updateCafe(Long cafeId, CafeRequest cafeRequest) throws IOException {
        log.info("Updating cafe with id: {}", cafeId);
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new RuntimeException(SystemMessage.CAFE_NOT_FOUND));
        cafe.setCafeName(cafeRequest.getCafeName());
        if(cafeRequest.getCafeLogo() != null) {
            cafe.setCafeLogo(fileHandler.saveMediaFile(cafeRequest.getCafeLogo(), "cafe").getFilePath());
        }
        cafe.setCafeLocation(cafeRequest.getCafeLocation());
        cafe.setCafeDescription(cafeRequest.getCafeDescription());
        if(cafeRequest.getCafeBanner() != null) {
            cafe.setCafeBanner(fileHandler.saveMediaFile(cafeRequest.getCafeBanner(), "cafe").getFilePath());
        }
        cafe.setCafeContact(cafeRequest.getCafeContact());
        cafe.setCafeEmail(cafeRequest.getCafeEmail());
        cafe.setCafeOpeningHours(cafe.getCafeOpeningHours());
        cafeRepository.save(cafe);
        return new CafeResponse(cafe, loggedInUser.getLoggedInUser());
    }

    @Override
    public void deleteCafe(Long cafeId) {
        log.info("Deleting cafe with id: {}", cafeId);
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new RuntimeException(SystemMessage.CAFE_NOT_FOUND));
        cafeRepository.delete(cafe);
    }
}
