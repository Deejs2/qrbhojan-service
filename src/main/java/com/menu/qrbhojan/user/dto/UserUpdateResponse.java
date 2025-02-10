package com.menu.qrbhojan.user.dto;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.utils.ImageResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateResponse {
    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String profileImage;
    private Boolean isActive;

    public UserUpdateResponse(Users user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.profileImage = ImageResponse.setImageUrl(SystemMessage.IMAGE_API_PATH, user.getProfileImage());
        this.isActive = user.getIsActive();
    }
}
