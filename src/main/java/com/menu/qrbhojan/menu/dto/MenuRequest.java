package com.menu.qrbhojan.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String menuName;
    private String description;
    private boolean status;
    private Long categoryId;
    private String cafeSpecialId;
}
