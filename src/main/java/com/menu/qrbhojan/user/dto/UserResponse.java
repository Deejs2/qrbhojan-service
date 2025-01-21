package com.menu.qrbhojan.user.dto;

import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.user.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private List<String> role;
    private String address;
    private String phone;
    private String profileImage;
    private Boolean isActive;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();        this.role = user.getRole().stream().map(Role::getName).toList();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.profileImage = user.getProfileImage();
        this.isActive = user.getIsActive();
    }
}
