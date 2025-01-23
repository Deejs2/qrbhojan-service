package com.menu.qrbhojan.website.dto;

import com.menu.qrbhojan.website.entity.CafeWebsite;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CafeWebsiteResponse {
    private Long cafeWebsiteId;
    private String cafeWebsiteUrl;
    private String cafeWebsiteQrCode;
    private String cafeWebsiteStatus;

    public CafeWebsiteResponse(CafeWebsite cafeWebsite) {
        this.cafeWebsiteId = cafeWebsite.getCafeWebsiteId();
        this.cafeWebsiteUrl = cafeWebsite.getCafeWebsiteUrl();
        this.cafeWebsiteQrCode = cafeWebsite.getCafeWebsiteQrCode();
        this.cafeWebsiteStatus = cafeWebsite.getCafeWebsiteStatus().name();
    }
}
