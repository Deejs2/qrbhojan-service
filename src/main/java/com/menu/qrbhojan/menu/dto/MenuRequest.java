package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu.entity.MenuItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String menuName;
    private String description;
    private Double price;
    private String image;
    private boolean isSpecial;
    private boolean availabilityStatus;
    private MenuItemStatus menuItemStatus;
    private Long categoryId;
    private String cafeSpecialId;
    private String tags;
}
