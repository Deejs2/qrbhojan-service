package com.menu.qrbhojan.website.dto;

import com.menu.qrbhojan.website.entity.CafeWebsite;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Base64;

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
        this.cafeWebsiteQrCode = encodeToBase64(cafeWebsite.getCafeWebsiteQrCode());
        this.cafeWebsiteStatus = cafeWebsite.getCafeWebsiteStatus().name();
    }

    private String encodeToBase64(byte[] qrInByte){
        if(qrInByte==null){
            return null;
        }
        return Base64.getEncoder().encodeToString(qrInByte);
    }
}
