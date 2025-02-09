package com.menu.qrbhojan.menu_items.service;

import com.menu.qrbhojan.menu_items.dto.MenuItemRequest;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponse;
import com.menu.qrbhojan.menu_items.dto.UpdateMenuItemRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface MenuItemService {
    MenuItemResponse saveMenuItem(MenuItemRequest menuItemRequest) throws IOException;

    Page<MenuItemResponse> getAllMenuItems(Pageable pageable);

    MenuItemResponse updateMenuItem(UpdateMenuItemRequest updateMenuItemRequest) throws IOException;

    String deleteMenuItem(Long id);
}
