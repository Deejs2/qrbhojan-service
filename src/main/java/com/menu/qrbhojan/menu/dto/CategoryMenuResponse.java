package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryMenuResponse {
    private MenuCategoryResponse category;
    private List<MenusResponse> menus;


    public CategoryMenuResponse(MenuCategoryResponse categoryResponse, List<MenusResponse> menuResponses) {
        this.category = categoryResponse;
        this.menus = menuResponses;
    }
}
