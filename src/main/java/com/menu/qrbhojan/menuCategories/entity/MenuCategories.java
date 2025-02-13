package com.menu.qrbhojan.menuCategories.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.menu.qrbhojan.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private String cafeSpecialId;

    @OneToMany(mappedBy = "menuCategories", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Menu> menus;
}
