package com.menu.qrbhojan.socialsite.service;

import com.menu.qrbhojan.socialsite.dto.SocialIconLinkRequest;
import com.menu.qrbhojan.socialsite.dto.SocialIconLinkResponse;
import com.menu.qrbhojan.socialsite.entity.SocialIcon;

import java.util.List;

public interface SocialIconLinkService {
    SocialIconLinkResponse saveSocialIconLink(SocialIconLinkRequest socialIconLinkRequest);
    List<SocialIconLinkResponse> getAllSocialIconLinks();
    SocialIconLinkResponse updateSocialIconLink(Long id, SocialIconLinkRequest socialIconLinkRequest);
    SocialIconLinkResponse getSocialIconLinkById(Long id);
    void deleteSocialIconLinkById(Long id);
    List<SocialIcon> getAllSocialIcons();
}
