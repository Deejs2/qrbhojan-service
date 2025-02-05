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
    private String description;
    private boolean status;
    private String cafeSpecialId;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private MenuCategories menuCategories;

}
