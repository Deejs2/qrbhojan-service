package com.menu.qrbhojan.menuCategories.service.impl;

import com.menu.qrbhojan.menu.dto.MenusResponse;
import com.menu.qrbhojan.menu.entity.Menu;
import com.menu.qrbhojan.menu.repository.MenuRepository;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.request.UpdateMenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import com.menu.qrbhojan.menuCategories.dto.response.UserMenuResponse;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import com.menu.qrbhojan.menuCategories.repository.MenuCategoryRepository;
import com.menu.qrbhojan.menuCategories.service.MenuCategoryService;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponses;
import com.menu.qrbhojan.menu_items.entity.MenuItem;
import com.menu.qrbhojan.menu_items.repository.MenuItemRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuCategoryServiceImpl implements MenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;
    private final LoggedInUser loggedInUserUtil;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuCategoryResponse> createMenuCategories(List<MenuCategoryRequest> menuCategoryRequest) {
        log.info("Creating menu category with name");

        List<MenuCategoryResponse> menuCategoryResponses = new ArrayList<>();
        menuCategoryRequest.forEach(requests ->{
            MenuCategories menuCategories = new MenuCategories();
           menuCategories.setCategoryName(requests.getName());
           menuCategories.setCategoryDescription(requests.getDescription());
           menuCategories.setCafeSpecialId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId());
            MenuCategories savedMenuCategories = menuCategoryRepository.save(menuCategories);
            menuCategoryResponses.add(new MenuCategoryResponse(savedMenuCategories));
        });
        log.info("Menu Category saved successfully.");
        return menuCategoryResponses;
    }

    @Override
    public Page<MenuCategoryResponse> getAllMenuCategories(Pageable pageable) {
            log.info("Getting all menu category");
            Page<MenuCategories> menuCategories = menuCategoryRepository.findAllByCafeSpecialId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId(), pageable);
            List<MenuCategoryResponse> menuCategoryResponses = menuCategories.stream().map(MenuCategoryResponse::new).toList();
            return new PageImpl<>(menuCategoryResponses, pageable, menuCategories.getTotalElements());
    }

    @Override
    public String deleteMenuCategory(Long id) {
        log.info("Deleting menu category with id: {}", id);
        MenuCategories menuCategories = menuCategoryRepository.findByCafeSpecialIdAndCategoryId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId(),id);
        menuCategoryRepository.delete(menuCategories);
        log.info("Category deleted successfully.");
        return "Category deleted successfully with Category Id: " + id;
    }

    @Override
    public MenuCategoryResponse updateMenuCategory(UpdateMenuCategoryRequest updateMenuCategoryRequest) {
        log.info("Updating menu category request received for categoryId: {}", updateMenuCategoryRequest.getCategoryId());

        MenuCategories menuCategories = menuCategoryRepository.findByCategoryIdAndCafeSpecialId(updateMenuCategoryRequest.getCategoryId(), loggedInUserUtil.getLoggedInCafe().getCafeSpecialId());
        if (menuCategories == null) {
            log.error("Menu category with id {} not found", updateMenuCategoryRequest.getCategoryId());
            throw new RuntimeException("Menu category not found");
        }

        menuCategories.setCategoryName(updateMenuCategoryRequest.getName());
        menuCategories.setCategoryDescription(updateMenuCategoryRequest.getDescription());

        MenuCategories savedMenuCategories = menuCategoryRepository.save(menuCategories);
        log.info("Menu Category updated successfully with id: {}", updateMenuCategoryRequest.getCategoryId());

        return new MenuCategoryResponse(savedMenuCategories);
    }

    @Override
    public Page<UserMenuResponse> getAllMenu(String cafeSpecialId, Pageable pageable) {
        log.info("Getting all menu for cafe special id: {}", cafeSpecialId);

        // Fetch all categories for the given cafeSpecId
        Page<MenuCategories> menuCategoriesPage = menuCategoryRepository.findAllByCafeSpecialId(cafeSpecialId, pageable);
        List<MenuCategories> menuCategoriesList = menuCategoriesPage.getContent();

        List<UserMenuResponse> userMenuResponses = new ArrayList<>();

        // Iterate through each category
        for (MenuCategories category : menuCategoriesList) {
            // Fetch all menus for the current category
            List<Menu> menus = menuRepository.findByMenuCategoriesCategoryId(category.getCategoryId());
            List<MenusResponse> menusResponses = new ArrayList<>();

            // Iterate through each menu
            for (Menu menu : menus) {
                // Fetch all menu items for the current menu
                List<MenuItem> menuItems = menuItemRepository.findMenuItemByMenu_MenuId(menu.getMenuId());
                List<MenuItemResponses> menuItemResponses = menuItems.stream().map(MenuItemResponses::new).collect(Collectors.toList());

                // Create MenusResponse for the current menu
                MenusResponse menusResponse = new MenusResponse(menu.getMenuId(), menu.getMenuName(),  menu.getDescription(), menu.getCafeSpecialId(), menuItemResponses);
                menusResponses.add(menusResponse);
            }

            // Create UserMenuResponse for the current category
            UserMenuResponse userMenuResponse = new UserMenuResponse(
                    new MenuCategoryResponse(category.getCategoryId(), category.getCategoryName(), category.getCategoryDescription(), category.getCafeSpecialId()),
                    menusResponses
            );

            userMenuResponses.add(userMenuResponse);
        }

        return new PageImpl<>(userMenuResponses, pageable, menuCategoriesPage.getTotalElements());
    }
}
