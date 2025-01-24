package com.menu.qrbhojan.menuCategories.dto.response;

import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String cafeSpecialId;


    public MenuCategoryResponse(MenuCategories savedMenuCategories) {
        this.id = savedMenuCategories.getCategoryId();
        this.name = savedMenuCategories.getCategoryName();
        this.description = savedMenuCategories.getCategoryDescription();
        this.cafeSpecialId = savedMenuCategories.getCafeSpecialId();
    }
}
