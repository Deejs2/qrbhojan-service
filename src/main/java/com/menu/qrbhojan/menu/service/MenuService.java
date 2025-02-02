package com.menu.qrbhojan.menu.service;

import com.menu.qrbhojan.menu.dto.MenuResponse;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;

public interface MenuService {
    MenuResponse createMenuCategories(MenuCategoryRequest menuCategoryRequest);
}
