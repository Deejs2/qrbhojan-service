package com.menu.qrbhojan.socialsite.dto;

import com.menu.qrbhojan.socialsite.entity.SocialIcon;
import com.menu.qrbhojan.socialsite.entity.SocialIconLink;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialIconLinkResponse {
    private Long socialIconLinkId;
    private SocialIcon socialIcon;
    private String socialIconLinkUrl;

    public SocialIconLinkResponse(SocialIconLink socialIconLink) {
        this.socialIconLinkId = socialIconLink.getSocialIconLinkId();
        this.socialIcon = socialIconLink.getSocialIcon();
        this.socialIconLinkUrl = socialIconLink.getSocialIconLinkUrl();
    }
}
