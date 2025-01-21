package com.menu.qrbhojan.auth.controller;

import com.menu.qrbhojan.auth.dto.AuthRequest;
import com.menu.qrbhojan.auth.dto.AuthResponse;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.security.JwtUtil;
import com.menu.qrbhojan.security.UserDetailsServiceImpl;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import com.menu.qrbhojan.utils.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;

    @PostMapping("/login")
    public ResponseEntity<GlobalApiResponse> createToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());

        Users user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
        user.setIsActive(true);
        userRepository.save(user);

        final String jwt = jwtUtil.generateToken(userDetails);
        return successResponse(new AuthResponse(jwt));
    }

    @GetMapping("/logout")
    public ResponseEntity<GlobalApiResponse> logout() {
        Users user = userRepository.findById(loggedInUser.getLoggedInUser().getId()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
        user.setIsActive(false);
        userRepository.save(user);
        return successResponse(SystemMessage.USER_LOGGED_OUT_MSG);
    }
}
