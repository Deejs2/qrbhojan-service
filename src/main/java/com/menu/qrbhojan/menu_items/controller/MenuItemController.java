package com.menu.qrbhojan.menu_items.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.menu_items.dto.MenuItemRequest;
import com.menu.qrbhojan.menu_items.dto.UpdateMenuItemRequest;
import com.menu.qrbhojan.menu_items.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/menuItem")
@RequiredArgsConstructor
public class MenuItemController extends BaseController {
    private final MenuItemService menuItemService;

    @PostMapping("/save")
    public ResponseEntity<GlobalApiResponse> saveMenuItem(@ModelAttribute MenuItemRequest menuItemRequest) throws IOException {
        return successResponse(menuItemService.saveMenuItem(menuItemRequest), SystemMessage.CREATE_MENU_ITEM);
    }

    @GetMapping("/getAllMenuItems")
    public ResponseEntity<GlobalApiResponse> getAllMenuItems(Pageable pageable) {
        return successResponse(menuItemService.getAllMenuItems(pageable), SystemMessage.GET_ALL_MENU_ITEMS);
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateMenuItem(@ModelAttribute UpdateMenuItemRequest updateMenuItemRequest) throws IOException {
        return successResponse(menuItemService.updateMenuItem(updateMenuItemRequest), SystemMessage.UPDATE_MENU_ITEM);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteMenuItem(@PathVariable Long id) {
        return successResponse(menuItemService.deleteMenuItem(id), SystemMessage.DELETE_MENU_ITEM);
    }


}
