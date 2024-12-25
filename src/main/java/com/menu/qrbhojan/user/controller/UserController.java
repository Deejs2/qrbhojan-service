package com.menu.qrbhojan.user.controller;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import com.menu.qrbhojan.user.dto.UserRequest;
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
}
