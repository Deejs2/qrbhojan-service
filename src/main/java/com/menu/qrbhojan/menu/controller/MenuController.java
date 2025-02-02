package com.menu.qrbhojan.menu.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.menu.service.MenuService;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController extends BaseController {
    private final MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createMenuCategories(@ModelAttribute MenuCategoryRequest menuCategoryRequest) {
        return successResponse(menuService.createMenuCategories(menuCategoryRequest), SystemMessage.MENU_CREATED);
    }
}
