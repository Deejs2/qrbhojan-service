package com.menu.qrbhojan.cafe.dto;

import com.menu.qrbhojan.cafe.entity.Cafe;
import com.menu.qrbhojan.user.entity.Users;
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
    private String cafeOwner;

    public CafeResponse(Cafe cafe, Users user) {
        this.cafeId = cafe.getCafeId();
        this.cafeName = cafe.getCafeName();
        this.cafeLogo = cafe.getCafeLogo();
        this.cafeLocation = cafe.getCafeLocation();
        this.cafeDescription = cafe.getCafeDescription();
        this.cafeBanner = cafe.getCafeBanner();
        this.cafeContact = cafe.getCafeContact();
        this.cafeEmail = cafe.getCafeEmail();
        this.cafeSpecialId = cafe.getCafeSpecialId();
        this.cafeOwner = user.getFullName();
    }

}
