package com.menu.qrbhojan.auth.service;

import com.menu.qrbhojan.auth.dto.UserRegisterRequest;
import com.menu.qrbhojan.auth.dto.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
}
