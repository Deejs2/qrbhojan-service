package com.menu.qrbhojan.menuCategories.service;

import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuCategoryService {
    MenuCategoryResponse createMenuCategories(MenuCategoryRequest menuCategoryRequest);

    Page<MenuCategoryResponse> getAllMenuCategories(Pageable pageable);

    String deleteMenuCategory(Long id);
}
