package com.menu.qrbhojan.menu.service.impl;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.menu.dto.MenuRequest;
import com.menu.qrbhojan.menu.dto.MenuResponse;
import com.menu.qrbhojan.menu.dto.UpdateMenuRequest;
import com.menu.qrbhojan.menu.entity.Menu;
import com.menu.qrbhojan.menu.entity.MenuItemStatus;
import com.menu.qrbhojan.menu.repository.MenuRepository;
import com.menu.qrbhojan.menu.service.MenuService;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import com.menu.qrbhojan.menuCategories.repository.MenuCategoryRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final FileHandler fileHandler;
    private final LoggedInUser loggedInUser;
    private final MenuCategoryRepository menuCategoryRepository;


    @Override
    public MenuResponse createMenuCategories(MenuRequest menuRequest) throws IOException {
        log.info("Menu request received");
        Menu menu = new Menu();
        menu.setMenuName(menuRequest.getMenuName());
        menu.setDescription(menuRequest.getDescription());
        menu.setPrice(menuRequest.getPrice());
        menu.setSpecial(menuRequest.isSpecial());
        menu.setAvailabilityStatus(menuRequest.isAvailabilityStatus());
        menu.setMenuItemStatus(menuRequest.getMenuItemStatus());
        menu.setTags(menuRequest.getTags());
        menu.setMenuCategories(menuCategoryRepository.findById(menuRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category ID: " + menuRequest.getCategoryId())));
        menu.setImage(fileHandler.saveMediaFile(menuRequest.getImage(), "menu").getFilePath());
        menu.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        menuRepository.save(menu);
        return new MenuResponse(menu);

    }

    @Override
    public Page<MenuResponse> getAllMenuCategories(Pageable pageable) {
        log.info("Getting all menu categories");
        Page<Menu> menu = menuRepository.findAll(pageable);
        List<MenuResponse> menuResponses = menu.stream().map(MenuResponse::new).toList();
        return new PageImpl<>(menuResponses, pageable, menu.getTotalElements());
    }

    @Override
    public String deleteMenuCategory(Long id) {
        log.info("Deleting menu category with id: {}", id);
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + id));
        menuRepository.delete(menu);
        log.info("Menu deleted successfully");
        return "Menu deleted successfully with Id: " + id;
    }

    @Override
    public Page<MenuResponse> getMenuByCafeSpecialId(Pageable pageable) {
        log.info("Getting all menu categories by cafe special Id : {}", loggedInUser.getLoggedInCafe().getCafeSpecialId());
        Page<Menu> menuPage = menuRepository.findMenuByAvailabilityStatusAndCafeSpecialId(true, loggedInUser.getLoggedInCafe().getCafeSpecialId(), pageable);
        List<MenuResponse> menuResponses = menuPage.stream().map(MenuResponse::new).toList();
        return new PageImpl<>(menuResponses, pageable, menuPage.getTotalElements());
    }

    @Override
    public MenuResponse updateMenu(UpdateMenuRequest updateMenuRequest) throws IOException {
        log.info("Updating menu with id: {}", updateMenuRequest.getMenuId());
        Menu menu = menuRepository.findByMenuIdAndCafeSpecialIdAndAvailabilityStatus(updateMenuRequest.getMenuId(), loggedInUser.getLoggedInCafe().getCafeSpecialId(), true);
        if(menu == null){
            throw new EntityNotFoundException("Menu not found with ID: " + updateMenuRequest.getMenuId());
        }
        menu.setMenuName(updateMenuRequest.getMenuName());
        menu.setDescription(updateMenuRequest.getDescription());
        menu.setPrice(updateMenuRequest.getPrice());
        menu.setSpecial(updateMenuRequest.isSpecial());
        menu.setAvailabilityStatus(updateMenuRequest.isAvailabilityStatus());
        menu.setMenuItemStatus(MenuItemStatus.valueOf(updateMenuRequest.getMenuItemStatus().toString().toUpperCase()));
        menu.setTags(updateMenuRequest.getTags());
        menu.setMenuCategories(menuCategoryRepository.findById(updateMenuRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Menu Category not found with Category ID: " + updateMenuRequest.getCategoryId())));
        if(updateMenuRequest.getImage() != null) {
            menu.setImage(fileHandler.saveMediaFile(updateMenuRequest.getImage(), "menu").getFilePath());
        }
        menu.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        menuRepository.save(menu);
       return new MenuResponse(menu);
    }
}
