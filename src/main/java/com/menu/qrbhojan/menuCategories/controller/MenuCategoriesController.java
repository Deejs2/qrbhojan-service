package com.menu.qrbhojan.menuCategories.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.request.UpdateMenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.service.MenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menuCategory")
public class MenuCategoriesController extends BaseController {
    private final MenuCategoryService menuCategoryService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createMenuCategories(@RequestBody  MenuCategoryRequest menuCategoryRequest) {
        return successResponse(menuCategoryService.createMenuCategories(menuCategoryRequest), SystemMessage.MENU_CATEGORY_CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAllMenuCategories(Pageable pageable) {
        return successResponse(menuCategoryService.getAllMenuCategories(pageable), SystemMessage.MENU_CATEGORY_FETCHED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteMenuCategory(@PathVariable Long id) {
        return successResponse(menuCategoryService.deleteMenuCategory(id), SystemMessage.MENU_CATEGORY_DELETED);
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateMenuCategory(@RequestBody UpdateMenuCategoryRequest updateMenuCategoryRequest) {
        return successResponse(menuCategoryService.updateMenuCategory(updateMenuCategoryRequest), SystemMessage.MENU_CATEGORY_UPDATED);
    }
}
