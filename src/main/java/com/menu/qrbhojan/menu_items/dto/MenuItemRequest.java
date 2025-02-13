package com.menu.qrbhojan.menu_items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest implements MenuItemRequestBase {
    private String menuItemName;
    private String description;
    private MultipartFile image;
    private Double price;
    private String tags;
    private Long menuId;
    private String menuItemStatus;
    private String availabilityStatus;
    private Boolean isSpecial;
}
