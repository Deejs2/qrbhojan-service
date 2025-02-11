package com.menu.qrbhojan.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenusRequest {
    private String menuName;
    private String description;
}
