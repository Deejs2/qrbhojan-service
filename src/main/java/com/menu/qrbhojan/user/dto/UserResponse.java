package com.menu.qrbhojan.user.dto;

import com.menu.qrbhojan.user.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String role;
    private String address;
    private String phone;
    private String profileImage;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole().getName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.profileImage = user.getProfileImage();
    }
}
