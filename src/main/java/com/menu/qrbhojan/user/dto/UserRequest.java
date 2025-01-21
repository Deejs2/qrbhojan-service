package com.menu.qrbhojan.user.dto;

import com.menu.qrbhojan.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private String address;
    private String phone;
    private MultipartFile profileImage;
}
