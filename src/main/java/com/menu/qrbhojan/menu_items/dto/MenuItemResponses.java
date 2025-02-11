package com.menu.qrbhojan.menu_items.dto;

import com.menu.qrbhojan.menu_items.entity.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponses {
    private Long menuItemId;
    private String menuItemName;
    private String description;
    private String image;
    private Double price;
    private String tags;
    private String menuItemStatus;
    private String availabilityStatus;
    private boolean isSpecial;

    public MenuItemResponses(MenuItem menuItem) {
        this.menuItemId = menuItem.getMenuItemId();
        this.menuItemName = menuItem.getMenuItemName();
        this.description = menuItem.getDescription();
        this.image = menuItem.getImage();
        this.price = menuItem.getPrice();
        this.tags = menuItem.getTags();
        this.menuItemStatus = String.valueOf(menuItem.getMenuItemStatus());
        this.availabilityStatus = String.valueOf(menuItem.getAvailabilityStatus());
        this.isSpecial = menuItem.getIsSpecial();
    }
}
