package com.menu.qrbhojan.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CafeWebsite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cafeWebsiteId;
    private String cafeWebsiteUrl;
    @Lob
    private byte[] cafeWebsiteQrCode;
    private WebsiteStatus cafeWebsiteStatus;
    private String cafeSpecialId;
}
