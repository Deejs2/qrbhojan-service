package com.menu.qrbhojan.menu.service.impl;


import com.menu.qrbhojan.menu.dto.*;
import com.menu.qrbhojan.menu.entity.Menu;
import com.menu.qrbhojan.menu.repository.MenuRepository;
import com.menu.qrbhojan.menu.service.MenuService;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import com.menu.qrbhojan.menuCategories.repository.MenuCategoryRepository;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponse;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponses;
import com.menu.qrbhojan.menu_items.entity.MenuItem;
import com.menu.qrbhojan.menu_items.repository.MenuItemRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final LoggedInUser loggedInUser;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemRepository menuItemRepository;


    @Override
    public List<MenuResponse> createMenu(MenuRequest menuRequest){
            log.info("Menu request received for category ID: {}", menuRequest.getCategoryId());
            MenuCategories menuCategory = menuCategoryRepository.findById(menuRequest.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category ID: " + menuRequest.getCategoryId()));

            List<MenuResponse> menuResponses = new ArrayList<>();
            menuRequest.getMenusRequests().forEach(menusRequest -> {
                Menu menu = new Menu();
                menu.setMenuName(menusRequest.getMenuName());
                menu.setDescription(menusRequest.getDescription());
                menu.setMenuCategories(menuCategory);
                menu.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
                menuRepository.save(menu);
                menuResponses.add(new MenuResponse(menu));
            });
            return menuResponses;

    }

    @Override
    public Page<MenuResponse> getAllMenu(Pageable pageable) {
        log.info("Getting all menu categories");
        Page<Menu> menu = menuRepository.findAll(pageable);
        List<MenuResponse> menuResponses = menu.stream().map(MenuResponse::new).toList();
        return new PageImpl<>(menuResponses, pageable, menu.getTotalElements());
    }

    @Override
    public String deleteMenu(Long id) {
        log.info("Deleting menu category with id: {}", id);
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + id));
        menuRepository.delete(menu);
        log.info("Menu deleted successfully");
        return "Menu deleted successfully with Id: " + id;
    }

    @Override
    public Page<MenuResponse> getMenuByCafeSpecialId(Pageable pageable) {
        log.info("Getting all menu categories by cafe special Id : {}", loggedInUser.getLoggedInCafe().getCafeSpecialId());
        Page<Menu> menuPage = menuRepository.findMenuByCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId(), pageable);
        List<MenuResponse> menuResponses = menuPage.stream().map(MenuResponse::new).toList();
        return new PageImpl<>(menuResponses, pageable, menuPage.getTotalElements());
    }

    @Override
    public MenuResponse updateMenu(UpdateMenuRequest updateMenuRequest){
        log.info("Updating menu with id: {}", updateMenuRequest.getMenuId());
        Menu menu = menuRepository.findByMenuIdAndCafeSpecialId(updateMenuRequest.getMenuId(), loggedInUser.getLoggedInCafe().getCafeSpecialId());
        if(menu == null){
            throw new EntityNotFoundException("Menu not found with ID: " + updateMenuRequest.getMenuId());
        }
        menu.setMenuName(updateMenuRequest.getMenuName());
        menu.setDescription(updateMenuRequest.getDescription());
        menu.setMenuCategories(menuCategoryRepository.findById(updateMenuRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category ID: " + updateMenuRequest.getCategoryId())));
        menu.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        menuRepository.save(menu);
       return new MenuResponse(menu);
    }

    @Override
    public Page<CategoryMenuResponse> getMenuByCategory(Long categoryId, Pageable pageable) {
        log.info("Getting all menu categories by category Id : {}", categoryId);
        MenuCategories category = menuCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category ID: " + categoryId));
        Page<Menu> menuPage = menuRepository.findMenuByCafeSpecialIdAndMenuCategoriesCategoryId(loggedInUser.getLoggedInCafe().getCafeSpecialId(), pageable, categoryId);
        List<MenusResponse> menuResponses = menuPage.stream().map(MenusResponse::new).toList();
        MenuCategoryResponse categoryResponse = new MenuCategoryResponse(category.getCategoryId(), category.getCategoryName(), category.getCategoryDescription(), category.getCafeSpecialId());
        CategoryMenuResponse categoryMenuResponse = new CategoryMenuResponse(categoryResponse, menuResponses);
        return new PageImpl<>(List.of(categoryMenuResponse), pageable, menuPage.getTotalElements());
    }

    @Override
    public Page<CategorySearchResponse> getMenuByCategoryName(String categoryName, Pageable pageable) {
        log.info("Getting all menus by category name: {} and cafeSpecId: {}", categoryName, loggedInUser.getLoggedInCafe().getCafeSpecialId());
        String cafeSpecId = loggedInUser.getLoggedInCafe().getCafeSpecialId();
        MenuCategories category = menuCategoryRepository.findByCategoryNameIgnoreCaseAndCafeSpecialId(categoryName, cafeSpecId)
                .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category Name: " + categoryName));
        Page<Menu> menuPage = menuRepository.findMenuByCafeSpecialIdAndMenuCategoriesCategoryId(cafeSpecId, pageable, category.getCategoryId());
        List<CategorySearchResponse> categorySearchResponses = menuPage.stream().map(menu -> {
            List<MenuItem> menuItems = menuItemRepository.findMenuItemByMenu_MenuId(menu.getMenuId());
            List<MenuItemResponses> menuItemResponses = menuItems.stream().map(MenuItemResponses::new).toList();
            return new CategorySearchResponse(new MenusResponse(menu), menuItemResponses);
        }).toList();
        return new PageImpl<>(categorySearchResponses, pageable, menuPage.getTotalElements());
    }

}
