package com.barisyenigun.ToDo.controller;

import com.barisyenigun.ToDo.entity.User;
import com.barisyenigun.ToDo.request.UserUpdateRequest;
import com.barisyenigun.ToDo.response.UserResponse;
import com.barisyenigun.ToDo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getUser(){
        return userService.getUser(userService.getAuthenticatedUserId());
    }

    @PostMapping("/me")
    public void updateUser(@RequestBody UserUpdateRequest body){
        userService.updateUser(userService.getAuthenticatedUserId(), body);
    }
}
