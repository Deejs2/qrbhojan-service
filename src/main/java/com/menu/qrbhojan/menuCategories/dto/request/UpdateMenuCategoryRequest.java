package com.menu.qrbhojan.menuCategories.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMenuCategoryRequest {
    private Long categoryId;
    private String name;
    private String description;
}
