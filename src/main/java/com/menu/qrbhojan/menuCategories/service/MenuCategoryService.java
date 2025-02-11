package com.menu.qrbhojan.menuCategories.service;

import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.request.UpdateMenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuCategoryService {
    List<MenuCategoryResponse> createMenuCategories(List<MenuCategoryRequest> menuCategoryRequest);

    Page<MenuCategoryResponse> getAllMenuCategories(Pageable pageable);

    String deleteMenuCategory(Long id);

    MenuCategoryResponse updateMenuCategory(UpdateMenuCategoryRequest updateMenuCategoryRequest);
}
