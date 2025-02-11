package com.menu.qrbhojan.socialsite.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.socialsite.dto.SocialIconLinkRequest;
import com.menu.qrbhojan.socialsite.service.SocialIconLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/social-icon-link")
public class SocialIconLinkController extends BaseController {
    private final SocialIconLinkService socialIconLinkService;
    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createSocialIconLink(@RequestBody SocialIconLinkRequest socialIconLinkRequest) {
        return successResponse(socialIconLinkService.saveSocialIconLink(socialIconLinkRequest), SystemMessage.SOCIAL_ICON_LINK_CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllSocialIconLinks() {
        return successResponse(socialIconLinkService.getAllSocialIconLinks(), SystemMessage.SOCIAL_ICON_LINK_FETCHED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalApiResponse> updateSocialIconLink(@PathVariable Long id, @RequestBody SocialIconLinkRequest socialIconLinkRequest) {
        return successResponse(socialIconLinkService.updateSocialIconLink(id, socialIconLinkRequest), SystemMessage.SOCIAL_ICON_LINK_UPDATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getSocialIconLinkById(@PathVariable Long id) {
        return successResponse(socialIconLinkService.getSocialIconLinkById(id), SystemMessage.SOCIAL_ICON_LINK_FETCHED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteSocialIconLinkById(@PathVariable Long id) {
        socialIconLinkService.deleteSocialIconLinkById(id);
        return successResponse(SystemMessage.SOCIAL_ICON_LINK_DELETED);
    }
    @GetMapping("/social-icons")
    public ResponseEntity<GlobalApiResponse> getAllSocialIcons() {
        return successResponse(socialIconLinkService.getAllSocialIcons(), SystemMessage.SOCIAL_ICON_LINK_FETCHED);
    }
}
