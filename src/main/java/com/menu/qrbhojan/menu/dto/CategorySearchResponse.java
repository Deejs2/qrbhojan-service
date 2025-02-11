package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu_items.dto.MenuItemResponse;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponses;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategorySearchResponse {
    private MenusResponse menu;
    private List<MenuItemResponses> menuItems;


    public CategorySearchResponse(MenusResponse menuResponse, List<MenuItemResponses> menuItemResponses) {
        this.menu = menuResponse;
        this.menuItems = menuItemResponses;

    }
}
