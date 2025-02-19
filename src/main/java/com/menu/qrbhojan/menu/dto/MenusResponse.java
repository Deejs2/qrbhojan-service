package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu.entity.Menu;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MenusResponse {
    private Long menuId;
    private String menuName;
    private String description;
    private String cafeSpecialId;
    private List<MenuItemResponses> menuItem;

    public MenusResponse(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.description = menu.getDescription();
        this.cafeSpecialId = menu.getCafeSpecialId();
    }

    public MenusResponse(Long menuId, String menuName, String description, String cafeSpecialId, List<MenuItemResponses> menuItemResponses) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.description = description;
        this.cafeSpecialId = cafeSpecialId;
        this.menuItem = menuItemResponses;
    }
}
