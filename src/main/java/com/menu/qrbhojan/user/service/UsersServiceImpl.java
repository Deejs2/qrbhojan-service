package com.menu.qrbhojan.user.service;

import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.role.repository.RoleRepository;
import com.menu.qrbhojan.user.dto.UserRequest;
import com.menu.qrbhojan.user.dto.UserResponse;
import com.menu.qrbhojan.user.dto.UserUpdateRequest;
import com.menu.qrbhojan.user.dto.UserUpdateResponse;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final FileHandler fileHandler;
    private final RoleRepository roleRepository;
    private final LoggedInUser loggedInUser;
    @Override
    public UserResponse createUser(UserRequest userRequest) throws IOException {
        log.info("Creating user: {}", userRequest);
        Users user = new Users();
        user.setFullName(userRequest.getFirstName() + " " + userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        Role role = roleRepository.findByName(userRequest.getRole()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.ROLE_NOT_FOUND)
        );
        user.setRole(List.of(role));
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

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map(UserResponse::new).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) throws IOException {
        log.info("Updating user: {}", userUpdateRequest);
        Users user = userRepository.findById(userUpdateRequest.getId()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
        user.setFullName(userUpdateRequest.getFirstName() + " " + userUpdateRequest.getLastName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setAddress(userUpdateRequest.getAddress());
        user.setPhone(userUpdateRequest.getPhone());
        if(userUpdateRequest.getProfileImage() != null) {
            user.setProfileImage(fileHandler.saveMediaFile(userUpdateRequest.getProfileImage(), "profileImages").getFilePath());
        }
        userRepository.save(user);
        log.info("User updated: {}", user);
        return new UserUpdateResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
        user.setIsDeleted(true);
        userRepository.save(user);
        log.info("User inactivated for 30 days : {}", user);
    }

    @Override
    public UserResponse getLoggedInUser() {
        log.info("Getting logged in user");
        return new UserResponse(loggedInUser.getLoggedInUser());
    }
}
