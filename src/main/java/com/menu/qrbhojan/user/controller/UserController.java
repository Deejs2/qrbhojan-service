package com.menu.qrbhojan.user.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.user.dto.UserRequest;
import com.menu.qrbhojan.user.dto.UserUpdateRequest;
import com.menu.qrbhojan.user.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {
    private final UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createUser(@ModelAttribute UserRequest userRequest) throws IOException {
        return successResponse(usersService.createUser(userRequest), SystemMessage.USER_CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllUsers(Pageable pageable) {
        return successResponse(usersService.getAllUsers(pageable), SystemMessage.USER_FETCHED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getUserById(@PathVariable Long id) {
        return successResponse(usersService.getUserById(id), SystemMessage.USER_FETCHED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return successResponse(SystemMessage.USER_DELETED);
    }
    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateUser(@ModelAttribute UserUpdateRequest userUpdateRequest) throws IOException {
        return successResponse(usersService.updateUser(userUpdateRequest), SystemMessage.USER_UPDATED);
    }
    @GetMapping("/loggedIn")
    public ResponseEntity<GlobalApiResponse> getLoggedInUser() {
        return successResponse(usersService.getLoggedInUser(), SystemMessage.USER_FETCHED);
    }
}
