package com.menu.qrbhojan.menu_items.entity;

import com.menu.qrbhojan.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;
    private String menuItemName;
    private String description;
    private Double price;
    private String image;
    private String tags;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;
    @Enumerated(EnumType.STRING)
    private MenuItemStatus menuItemStatus;
    private Boolean isSpecial;
    private String cafeSpecialId;
}
