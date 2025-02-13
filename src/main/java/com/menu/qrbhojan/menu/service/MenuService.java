package com.menu.qrbhojan.menu.service;

import com.menu.qrbhojan.menu.dto.*;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;


public interface MenuService {
    List<MenuResponse> createMenu(MenuRequest menuRequest);

    Page<MenuResponse> getAllMenu(Pageable pageable);

    String deleteMenu(Long id);

    Page<MenuResponse> getMenuByCafeSpecialId(Pageable pageable);

    MenuResponse updateMenu(UpdateMenuRequest updateMenuRequest);

    Page<CategoryMenuResponse> getMenuByCategory(Long categoryId, Pageable pageable);

    Page<CategorySearchResponse> getMenuByCategoryName(String categoryName, Pageable pageable);
}
