package com.menu.qrbhojan.cafe.dto;

import com.menu.qrbhojan.cafe.entity.Cafe;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.utils.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeResponse {
    private Long cafeId;
    private String cafeName;
    private String cafeLogo;
    private String cafeLocation;
    private String cafeDescription;
    private String cafeBanner;
    private String cafeContact;
    private String cafeEmail;
    private String cafeSpecialId;
    private String cafeOpeningHours;
    private String cafeOwner;

    public CafeResponse(Cafe cafe, Users user) {
        this.cafeId = cafe.getCafeId();
        this.cafeName = cafe.getCafeName();
        this.cafeLogo = ImageResponse.setImageUrl(SystemMessage.IMAGE_API_PATH, cafe.getCafeLogo());
        this.cafeLocation = cafe.getCafeLocation();
        this.cafeDescription = cafe.getCafeDescription();
        this.cafeBanner = ImageResponse.setImageUrl(SystemMessage.IMAGE_API_PATH, cafe.getCafeBanner());
        this.cafeContact = cafe.getCafeContact();
        this.cafeEmail = cafe.getCafeEmail();
        this.cafeSpecialId = cafe.getCafeSpecialId();
        this.cafeOpeningHours = cafe.getCafeOpeningHours();
        this.cafeOwner = user.getFullName();
    }

}
