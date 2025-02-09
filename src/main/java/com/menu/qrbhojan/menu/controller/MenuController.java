package com.menu.qrbhojan.menu.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.menu.dto.MenuRequest;
import com.menu.qrbhojan.menu.dto.UpdateMenuRequest;
import com.menu.qrbhojan.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController extends BaseController {
    private final MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createMenu(@RequestBody MenuRequest menuRequest){
        return successResponse(menuService.createMenu(menuRequest), SystemMessage.MENU_CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<GlobalApiResponse> getAllMenu(Pageable pageable) {
        return successResponse(menuService.getAllMenu(pageable), SystemMessage.MENU_FETCHED);
    }

    @GetMapping("/getMenu")
    public ResponseEntity<GlobalApiResponse> getMenuByCafeSpecialId(Pageable pageable) {
        return successResponse(menuService.getMenuByCafeSpecialId(pageable), SystemMessage.MENU_FETCHED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteMenu(@PathVariable Long id) {
        return successResponse(menuService.deleteMenu(id), SystemMessage.MENU_DELETED);
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateMenu(@RequestBody UpdateMenuRequest updateMenuRequest){
        return successResponse(menuService.updateMenu(updateMenuRequest), SystemMessage.MENU_UPDATED);
    }

    @GetMapping("/getMenuByCategory")
    public ResponseEntity<GlobalApiResponse> getMenuByCategory(@RequestParam Long categoryId, Pageable pageable) {
        return successResponse(menuService.getMenuByCategory(categoryId, pageable), SystemMessage.MENU_FETCHED);
    }
}
