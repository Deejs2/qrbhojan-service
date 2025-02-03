package com.menu.qrbhojan.menu.service;

import com.menu.qrbhojan.menu.dto.MenuRequest;
import com.menu.qrbhojan.menu.dto.MenuResponse;
import com.menu.qrbhojan.menu.dto.UpdateMenuRequest;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;


public interface MenuService {
    MenuResponse createMenuCategories(MenuRequest menuRequest) throws IOException;

    Page<MenuResponse> getAllMenuCategories(Pageable pageable);

    String deleteMenuCategory(Long id);

    Page<MenuResponse> getMenuByCafeSpecialId(Pageable pageable);

    MenuResponse updateMenu(UpdateMenuRequest updateMenuRequest) throws IOException;
}
