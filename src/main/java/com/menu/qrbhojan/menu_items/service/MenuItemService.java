package com.menu.qrbhojan.menu_items.service;

import com.menu.qrbhojan.menu_items.dto.request.MenuItemRequest;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponse;
import com.menu.qrbhojan.menu_items.dto.request.UpdateMenuItemRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> saveMenuItem(MenuItemRequest menuItemRequest) throws IOException;

    Page<MenuItemResponse> getAllMenuItems(Pageable pageable);

    MenuItemResponse updateMenuItem(UpdateMenuItemRequest updateMenuItemRequest) throws IOException;

    String deleteMenuItem(Long id);
}
