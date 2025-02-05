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
    private boolean status;
    private String cafeSpecialId;
    private MenuCategoryResponse category;

    public MenuResponse(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.description = menu.getDescription();
        this.status = menu.isStatus();
        this.cafeSpecialId = menu.getCafeSpecialId();
        this.category = new MenuCategoryResponse(menu.getMenuCategories());
    }
}
