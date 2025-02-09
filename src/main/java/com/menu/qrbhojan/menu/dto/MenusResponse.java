package com.menu.qrbhojan.menu.dto;

import com.menu.qrbhojan.menu.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenusResponse {
    private Long menuId;
    private String menuName;
    private String description;
    private boolean status;
    private String cafeSpecialId;

    public MenusResponse(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.description = menu.getDescription();
        this.status = menu.isStatus();
        this.cafeSpecialId = menu.getCafeSpecialId();
    }
}
