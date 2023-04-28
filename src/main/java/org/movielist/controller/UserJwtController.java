package org.movielist.controller;

import org.movielist.dto.request.RegisterRequest;
import org.movielist.dto.response.MLResponse;
import org.movielist.dto.response.ResponseMessage;
import org.movielist.security.jwt.JwtUtils;
import org.movielist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserJwtController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    public UserJwtController(UserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<MLResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        userService.saveUser(registerRequest);
        MLResponse mlResponse = new MLResponse();
        mlResponse.setStatus(true);
        mlResponse.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        return new ResponseEntity<>(mlResponse, HttpStatus.CREATED);

    }


}
