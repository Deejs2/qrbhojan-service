package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu.entity.MenuItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String menuName;
    private String description;
    private Double price;
    private MultipartFile image;
    private boolean isSpecial;
    private boolean availabilityStatus;
    private MenuItemStatus menuItemStatus;
    private Long categoryId;
    private String cafeSpecialId;
    private String tags;
}
