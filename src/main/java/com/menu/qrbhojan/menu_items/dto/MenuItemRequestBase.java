package com.menu.qrbhojan.menu_items.dto;

import org.springframework.web.multipart.MultipartFile;

public interface MenuItemRequestBase {
    String getMenuItemName();
    String getDescription();
    Double getPrice();

    MultipartFile getImage();
    String getTags();
    Long getMenuId();
    String getMenuItemStatus();
    String getAvailabilityStatus();
    Boolean getIsSpecial();
}
