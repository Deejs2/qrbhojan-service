package com.menu.qrbhojan.utils;

import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUser {
    private final UserRepository userRepository;

    public Users getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("No user found with email: " + email)
        );
    }

    public Role getLoggedInUserRole() {
        Users user = getLoggedInUser();
        return user.getRole();
    }
}
