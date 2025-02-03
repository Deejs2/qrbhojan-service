package com.menu.qrbhojan.menu.entity;

import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private String image;
    private String description;
    private Double price;
    private boolean isSpecial;
    private boolean availabilityStatus;
    @Enumerated(EnumType.STRING)
    private MenuItemStatus menuItemStatus;
    private String cafeSpecialId;
    private String tags;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private MenuCategories menuCategories;

}
