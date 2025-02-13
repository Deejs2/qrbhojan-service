package com.menu.qrbhojan.menu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import com.menu.qrbhojan.menu_items.entity.MenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private String description;
    private boolean status;
    private String cafeSpecialId;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonBackReference
    private MenuCategories menuCategories;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems;

}
