package com.menu.qrbhojan.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private Long categoryId;
    private List<MenusRequest> menusRequests;
}
