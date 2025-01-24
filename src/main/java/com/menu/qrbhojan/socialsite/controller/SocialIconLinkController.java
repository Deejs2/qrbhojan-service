package com.menu.qrbhojan.socialsite.controller;

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
        return successResponse(socialIconLinkService.saveSocialIconLink(socialIconLinkRequest));
    }
    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllSocialIconLinks() {
        return successResponse(socialIconLinkService.getAllSocialIconLinks());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalApiResponse> updateSocialIconLink(@PathVariable Long id, @RequestBody SocialIconLinkRequest socialIconLinkRequest) {
        return successResponse(socialIconLinkService.updateSocialIconLink(id, socialIconLinkRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getSocialIconLinkById(@PathVariable Long id) {
        return successResponse(socialIconLinkService.getSocialIconLinkById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteSocialIconLinkById(@PathVariable Long id) {
        socialIconLinkService.deleteSocialIconLinkById(id);
        return successResponse("Social icon link deleted successfully");
    }
    @GetMapping("/social-icons")
    public ResponseEntity<GlobalApiResponse> getAllSocialIcons() {
        return successResponse(socialIconLinkService.getAllSocialIcons());
    }
}
