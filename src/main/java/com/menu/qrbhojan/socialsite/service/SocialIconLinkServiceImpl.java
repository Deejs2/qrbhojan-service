package com.menu.qrbhojan.socialsite.service;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.socialsite.dto.SocialIconLinkRequest;
import com.menu.qrbhojan.socialsite.dto.SocialIconLinkResponse;
import com.menu.qrbhojan.socialsite.entity.SocialIcon;
import com.menu.qrbhojan.socialsite.entity.SocialIconLink;
import com.menu.qrbhojan.socialsite.repository.SocialIconLinkRepository;
import com.menu.qrbhojan.socialsite.repository.SocialIconRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialIconLinkServiceImpl implements SocialIconLinkService{
    private final SocialIconLinkRepository socialIconLinkRepository;
    private final SocialIconRepository socialIconRepository;
    private final LoggedInUser loggedInUser;
    @Override
    public SocialIconLinkResponse saveSocialIconLink(SocialIconLinkRequest socialIconLinkRequest) {
        log.info("Saving social icon link");

        SocialIcon socialIcon = socialIconRepository.findById(socialIconLinkRequest.getSocialIconId())
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.SOCIAL_ICON_NOT_FOUND));
        SocialIconLink socialIconLink = new SocialIconLink();
        socialIconLink.setSocialIcon(socialIcon);
        socialIconLink.setSocialIconLinkUrl(socialIconLinkRequest.getSocialIconLinkUrl());
        socialIconLink.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        socialIconLinkRepository.save(socialIconLink);
        return new SocialIconLinkResponse(socialIconLink);
    }

    @Override
    public List<SocialIconLinkResponse> getAllSocialIconLinks() {
        log.info("Fetching all social icon links");
        return socialIconLinkRepository.findAll().stream().map(SocialIconLinkResponse::new).toList();
    }

    @Override
    public SocialIconLinkResponse updateSocialIconLink(Long id, SocialIconLinkRequest socialIconLinkRequest) {
        log.info("Updating social icon link by id: {}", id);
        SocialIconLink socialIconLink = socialIconLinkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.SOCIAL_ICON_LINK_NOT_FOUND));
        SocialIcon socialIcon = socialIconRepository.findById(socialIconLinkRequest.getSocialIconId()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.SOCIAL_ICON_NOT_FOUND)
        );
        socialIconLink.setSocialIcon(socialIcon);
        socialIconLink.setSocialIconLinkUrl(socialIconLinkRequest.getSocialIconLinkUrl());
        socialIconLink.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        socialIconLinkRepository.save(socialIconLink);
        return new SocialIconLinkResponse(socialIconLink);
    }

    @Override
    public SocialIconLinkResponse getSocialIconLinkById(Long id) {
        log.info("Fetching social icon link by id: {}", id);
        SocialIconLink socialIconLink = socialIconLinkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.SOCIAL_ICON_LINK_NOT_FOUND));
        return new SocialIconLinkResponse(socialIconLink);
    }

    @Override
    public void deleteSocialIconLinkById(Long id) {
        log.info("Deleting social icon link by id: {}", id);
        SocialIconLink socialIconLink = socialIconLinkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.SOCIAL_ICON_LINK_NOT_FOUND));
        socialIconLinkRepository.delete(socialIconLink);
    }

    @Override
    public List<SocialIcon> getAllSocialIcons() {
        return socialIconRepository.findAll();
    }
}
