package com.menu.qrbhojan.menu_items.service.impl;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.menu.repository.MenuRepository;
import com.menu.qrbhojan.menu_items.dto.MenuItemRequest;
import com.menu.qrbhojan.menu_items.dto.MenuItemRequestBase;
import com.menu.qrbhojan.menu_items.dto.MenuItemResponse;
import com.menu.qrbhojan.menu_items.dto.UpdateMenuItemRequest;
import com.menu.qrbhojan.menu_items.entity.AvailabilityStatus;
import com.menu.qrbhojan.menu_items.entity.MenuItem;
import com.menu.qrbhojan.menu_items.entity.MenuItemStatus;
import com.menu.qrbhojan.menu_items.repository.MenuItemRepository;
import com.menu.qrbhojan.menu_items.service.MenuItemService;
import com.menu.qrbhojan.utils.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final FileHandler fileHandler;
    private final LoggedInUser loggedInUser;
    private final MenuRepository menuRepository;

    private void mapRequestToMenuItem(MenuItem menuItem, MenuItemRequestBase menuItemRequest) throws IOException {
        menuItem.setMenuItemName(menuItemRequest.getMenuItemName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setTags(menuItemRequest.getTags());
        menuItem.setMenuItemStatus(MenuItemStatus.valueOf(menuItemRequest.getMenuItemStatus().toUpperCase()));
        menuItem.setAvailabilityStatus(AvailabilityStatus.valueOf(menuItemRequest.getAvailabilityStatus().toUpperCase()));
        if (menuItemRequest.getImage() != null) {
            menuItem.setImage(fileHandler.saveMediaFile(menuItemRequest.getImage(), "menuItem").getFilePath());
        }
        menuItem.setIsSpecial(menuItemRequest.getIsSpecial());
        menuItem.setMenu(menuRepository.findById(menuItemRequest.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with the Id :" + menuItemRequest.getMenuId())));
    }

    @Override
    public MenuItemResponse saveMenuItem(MenuItemRequest menuItemRequest) throws IOException {
        log.info("MenuItem save request received");
        MenuItem menuItem = new MenuItem();
        menuItem.setCafeSpecialId(loggedInUser.getLoggedInCafe().getCafeSpecialId());
        mapRequestToMenuItem(menuItem, menuItemRequest);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        log.info("MenuItem saved successfully.");
        return new MenuItemResponse(savedMenuItem);
    }

    @Override
    public Page<MenuItemResponse> getAllMenuItems(Pageable pageable) {
        log.info("MenuItem fetch req received.");
        Page<MenuItem> menuItems = menuItemRepository.findAll(pageable);
        List<MenuItemResponse> menuItemResponses = menuItems.map(MenuItemResponse::new).toList();
        log.info("MenuItem fetched.");
        return new PageImpl<>(menuItemResponses, pageable, menuItems.getTotalElements());
    }

    @Override
    public MenuItemResponse updateMenuItem(UpdateMenuItemRequest updateMenuItemRequest) throws IOException {
        log.info("MenuItem update request received");
        MenuItem menuItem = menuItemRepository.findAllByMenuItemIdAndCafeSpecialId(updateMenuItemRequest.getMenuItemId(), loggedInUser.getLoggedInCafe().getCafeSpecialId())
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));
        mapRequestToMenuItem(menuItem, updateMenuItemRequest);
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        log.info("MenuItem updated successfully.");
        return new MenuItemResponse(updatedMenuItem);
    }

    @Override
    public String deleteMenuItem(Long id) {
        log.info("MenuItem delete request received");
        MenuItem menuItem = menuItemRepository.findAllByMenuItemIdAndCafeSpecialId(id, loggedInUser.getLoggedInCafe().getCafeSpecialId())
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));
        menuItemRepository.delete(menuItem);
        log.info("MenuItem deleted successfully.");
        return "Menu Item deleted successfully with Id: " + id;
    }
}
