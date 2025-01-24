package com.menu.qrbhojan.auth.service;

import com.menu.qrbhojan.auth.dto.UserRegisterRequest;
import com.menu.qrbhojan.auth.dto.UserRegisterResponse;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.exception.custom.EmailAlreadyExistsException;
import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.role.repository.RoleRepository;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        log.info("Registering user: {}", userRegisterRequest);
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userRegisterRequest.getEmail()))) {
            throw new EmailAlreadyExistsException(SystemMessage.EMAIL_ALREADY_EXISTS);
        }
        Users user = new Users();
        user.setEmail(userRegisterRequest.getEmail());
        user.setFullName(userRegisterRequest.getFullName());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.ROLE_NOT_FOUND)
        );
        user.setRole(List.of(role));

        userRepository.save(user);
        log.info("User registered: {}", user);

        return new UserRegisterResponse(user);
    }
}
