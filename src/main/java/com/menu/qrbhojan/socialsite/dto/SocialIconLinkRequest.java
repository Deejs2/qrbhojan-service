package com.menu.qrbhojan.socialsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialIconLinkRequest {
    private Long socialIconId;
    private String socialIconLinkUrl;
}
