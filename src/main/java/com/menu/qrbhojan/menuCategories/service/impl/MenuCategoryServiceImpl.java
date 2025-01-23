package com.menu.qrbhojan.menuCategories.service.impl;

import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.request.UpdateMenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import com.menu.qrbhojan.menuCategories.repository.MenuCategoryRepository;
import com.menu.qrbhojan.menuCategories.service.MenuCategoryService;
import com.menu.qrbhojan.utils.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuCategoryServiceImpl implements MenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;
    private final LoggedInUser loggedInUserUtil;

    @Override
    public MenuCategoryResponse createMenuCategories(MenuCategoryRequest menuCategoryRequest) {
        log.info("Creating menu category with name: {}", menuCategoryRequest.getName());
        MenuCategories menuCategories = new MenuCategories();
        menuCategories.setCategoryName(menuCategoryRequest.getName());
        menuCategories.setCategoryDescription(menuCategoryRequest.getDescription());
        menuCategories.setCafeSpecialId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId());
        MenuCategories savedMenuCategories = menuCategoryRepository.save(menuCategories);
        log.info("Menu Category saved successfully.");
        return new MenuCategoryResponse(savedMenuCategories);

    }

    @Override
    public Page<MenuCategoryResponse> getAllMenuCategories(Pageable pageable) {
            log.info("Getting all menu category");
            Page<MenuCategories> menuCategories = menuCategoryRepository.findAllByCafeSpecialId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId(), pageable);
            List<MenuCategoryResponse> menuCategoryResponses = menuCategories.stream().map(MenuCategoryResponse::new).collect(Collectors.toList());
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
    public MenuCategoryResponse updateMenuCategory(Long categoryId,UpdateMenuCategoryRequest menuCategoryRequest) {
        log.info("Updating menu category request received");
        MenuCategories menuCategories = menuCategoryRepository.findByCafeSpecialIdAndCategoryId(loggedInUserUtil.getLoggedInCafe().getCafeSpecialId(), categoryId);
        menuCategories.setCategoryName(menuCategoryRequest.getCategoryName());
        menuCategories.setCategoryDescription(menuCategoryRequest.getDescription());
        MenuCategories savedMenuCategories = menuCategoryRepository.save(menuCategories);
        log.info("Menu Category updated successfully.");
        return new MenuCategoryResponse(savedMenuCategories);
    }
}
