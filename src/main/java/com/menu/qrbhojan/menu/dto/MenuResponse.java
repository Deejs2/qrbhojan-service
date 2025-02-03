package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu.entity.Menu;
import com.menu.qrbhojan.menu.entity.MenuItemStatus;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private Long menuId;
    private String menuName;
    private String description;
    private Double price;
    private String image;
    private boolean isSpecial;
    private boolean availabilityStatus;
    private MenuItemStatus menuItemStatus;
    private String cafeSpecialId;
    private String tags;
    private MenuCategoryResponse category;

    public MenuResponse(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.image = menu.getImage();
        this.isSpecial = menu.isSpecial();
        this.availabilityStatus = menu.isAvailabilityStatus();
        this.menuItemStatus = menu.getMenuItemStatus();
        this.cafeSpecialId = menu.getCafeSpecialId();
        this.tags = menu.getTags();
        this.category = new MenuCategoryResponse(menu.getMenuCategories());
    }
}
