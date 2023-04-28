package org.movielist.service;

import org.movielist.domain.Role;
import org.movielist.domain.User;
import org.movielist.domain.enums.RoleType;
import org.movielist.dto.request.RegisterRequest;
import org.movielist.exception.ConflictException;
import org.movielist.exception.ResourceNotFoundException;
import org.movielist.exception.message.ErrorMessage;
import org.movielist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email){
       return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
    }

    public void saveUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()))
        }
        Role role = roleService.findByType(RoleType.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User();

        user.setRoles(roles);
        user.setPassword(encodedPassword);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setAddress(registerRequest.getAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setZipCode(registerRequest.getZipCode());

    }
}
