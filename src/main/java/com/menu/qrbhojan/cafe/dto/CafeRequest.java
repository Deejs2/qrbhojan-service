package com.menu.qrbhojan.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeRequest {
    private String cafeName;
    private MultipartFile cafeLogo;
    private String cafeLocation;
    private String cafeDescription;
    private MultipartFile cafeBanner;
    private String cafeContact;
    private String cafeEmail;
}
