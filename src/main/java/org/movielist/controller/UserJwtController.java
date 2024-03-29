package org.movielist.controller;

import org.movielist.dto.request.LoginRequest;
import org.movielist.dto.request.RegisterRequest;
import org.movielist.dto.response.LoginResponse;
import org.movielist.dto.response.MlResponse;
import org.movielist.dto.response.ResponseMessage;
import org.movielist.security.jwt.JwtUtils;
import org.movielist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<MlResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        userService.saveUser(registerRequest);
        MlResponse mlResponse = new MlResponse();
        mlResponse.setStatus(true);
        mlResponse.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        return new ResponseEntity<>(mlResponse, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid
                                                      @RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword());

        Authentication authentication =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // !!! Kullanıcı bu aşamada valide edildi ve Token üretimine geçiliyor
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        // !!! JWT token client tarafına gönderiliyor
        LoginResponse loginResponse = new LoginResponse(jwtToken);

        return new ResponseEntity<>(loginResponse,HttpStatus.OK);

    }
}

