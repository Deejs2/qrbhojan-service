package com.menu.qrbhojan.user.service;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.user.dto.UserRequest;
import com.menu.qrbhojan.user.dto.UserResponse;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final FileHandler fileHandler;
    @Override
    public UserResponse createUser(UserRequest userRequest) throws IOException {
        log.info("Creating user: {}", userRequest);
        Users user = new Users();
        user.setFullName(userRequest.getFirstName() + " " + userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setProfileImage(fileHandler.saveMediaFile(userRequest.getProfileImage(), "profileImages").getFilePath());
        userRepository.save(user);
        log.info("User created: {}", user);
        return new UserResponse(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        log.info("Getting all users");
        Page<Users> users = userRepository.findAll(pageable);
        return users.map(UserResponse::new);
    }
}
