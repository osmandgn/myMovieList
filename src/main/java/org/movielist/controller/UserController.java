package org.movielist.controller;

import org.movielist.domain.User;
import org.movielist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auht/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> gelAllUser(){

    }
}
