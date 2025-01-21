package com.menu.qrbhojan.user.service;

import com.menu.qrbhojan.user.dto.UserRequest;
import com.menu.qrbhojan.user.dto.UserResponse;
import com.menu.qrbhojan.user.dto.UserUpdateRequest;
import com.menu.qrbhojan.user.dto.UserUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface UsersService {
    UserResponse createUser(UserRequest userRequest) throws IOException;
    Page<UserResponse> getAllUsers(Pageable pageable);
    UserResponse getUserById(Long id);
    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) throws IOException;
    void deleteUser(Long id);
    UserResponse getLoggedInUser();
}
