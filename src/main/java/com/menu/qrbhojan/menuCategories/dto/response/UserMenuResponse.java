package com.menu.qrbhojan.menuCategories.dto.response;

import com.menu.qrbhojan.menu.dto.MenusResponse;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMenuResponse {

    private MenuCategoryResponse category;
    private List<MenusResponse> menu;
    private List<MenuItemResponses> menuItems;

    public UserMenuResponse(MenuCategoryResponse menuCategoryResponse, List<MenusResponse> menusResponses) {
        this.category = menuCategoryResponse;
        this.menu = menusResponses;
    }
}
