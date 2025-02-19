package com.menu.qrbhojan.menu_items.dto.request;

import com.menu.qrbhojan.menu_items.dto.MenuItemRequestBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {
    private Long menuId;
    private List<MenuItemRequests> menuItemRequests;

}
