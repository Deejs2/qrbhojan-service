package com.menu.qrbhojan.menu_items.dto;

import com.menu.qrbhojan.menu.dto.MenuResponse;
import com.menu.qrbhojan.menu_items.entity.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponse {
    private Long menuItemId;
    private String menuItemName;
    private String description;
    private String image;
    private Double price;
    private String tags;
    private String menuItemStatus;
    private String availabilityStatus;
    private boolean isSpecial;
    private MenuResponse menu;

    public MenuItemResponse(MenuItem savedMenuItem) {
        this.menuItemId = savedMenuItem.getMenuItemId();
        this.menuItemName = savedMenuItem.getMenuItemName();
        this.description = savedMenuItem.getDescription();
        this.image = savedMenuItem.getImage();
        this.price = savedMenuItem.getPrice();
        this.tags = savedMenuItem.getTags();
        this.menuItemStatus = String.valueOf(savedMenuItem.getMenuItemStatus());
        this.availabilityStatus = String.valueOf(savedMenuItem.getAvailabilityStatus());
        this.isSpecial = savedMenuItem.getIsSpecial();
        this.menu = new MenuResponse(savedMenuItem.getMenu());
    }
}
