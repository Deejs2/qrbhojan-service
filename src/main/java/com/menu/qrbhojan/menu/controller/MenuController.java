package com.menu.qrbhojan.menu.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.menu.dto.MenuRequest;
import com.menu.qrbhojan.menu.dto.UpdateMenuRequest;
import com.menu.qrbhojan.menu.service.MenuService;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController extends BaseController {
    private final MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createMenuCategories(@ModelAttribute MenuRequest menuRequest) throws IOException {
        return successResponse(menuService.createMenuCategories(menuRequest), SystemMessage.MENU_CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<GlobalApiResponse> getAllMenuCategories(Pageable pageable) {
        return successResponse(menuService.getAllMenuCategories(pageable), SystemMessage.MENU_FETCHED);
    }

    @GetMapping("/getMenu")
    public ResponseEntity<GlobalApiResponse> getMenuByCafeSpecialId(Pageable pageable) {
        return successResponse(menuService.getMenuByCafeSpecialId(pageable), SystemMessage.MENU_FETCHED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteMenuCategory(@PathVariable Long id) {
        return successResponse(menuService.deleteMenuCategory(id), SystemMessage.MENU_DELETED);
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateMenu(@ModelAttribute UpdateMenuRequest updateMenuRequest) throws IOException {
        return successResponse(menuService.updateMenu(updateMenuRequest), SystemMessage.MENU_UPDATED);
    }
}
