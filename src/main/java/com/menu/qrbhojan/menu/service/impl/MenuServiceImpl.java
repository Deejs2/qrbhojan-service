package com.menu.qrbhojan.menu.service.impl;

import com.menu.qrbhojan.menu.dto.MenuResponse;
import com.menu.qrbhojan.menu.repository.MenuRepository;
import com.menu.qrbhojan.menu.service.MenuService;
import com.menu.qrbhojan.menuCategories.dto.request.MenuCategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;


    @Override
    public MenuResponse createMenuCategories(MenuCategoryRequest menuCategoryRequest) {
        return null;
    }
}
