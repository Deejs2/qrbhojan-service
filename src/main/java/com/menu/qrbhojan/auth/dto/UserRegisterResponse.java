package com.menu.qrbhojan.auth.dto;

import com.menu.qrbhojan.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterResponse {
    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private Boolean isActive;

    public UserRegisterResponse(Users user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.isActive = user.getIsActive();
    }
}
