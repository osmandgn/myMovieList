package org.movielist.service;

import org.movielist.domain.Role;
import org.movielist.domain.User;
import org.movielist.domain.enums.RoleType;
import org.movielist.dto.UserDTO;
import org.movielist.dto.request.RegisterRequest;
import org.movielist.dto.request.UpdatePasswordRequest;
import org.movielist.exception.ConflictException;
import org.movielist.exception.ResourceNotFoundException;
import org.movielist.exception.message.ErrorMessage;
import org.movielist.mapper.UserMapper;
import org.movielist.repository.UserRepository;
import org.movielist.security.SecurityUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public User getUserByEmail(String email){
       return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
    }

    public void saveUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
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

        userRepository.save(user);

    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.map(userList);

    }

    public UserDTO getPrincipal() {
        return userMapper.userToUserDTO(getCurrentUser());
    }

    public User getCurrentUser(){
        String email = SecurityUtils.getCurrentUserLogin().
                orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRINCIPAL_FOUND_MESSAGE));
        return getUserByEmail(email);
    }

    public Page<UserDTO> getUserPage(Pageable pageable) {
        Page<User> usersPageAble = userRepository.findAll(pageable);
        return getUserDTOPage(usersPageAble);
    }

    private Page<UserDTO> getUserDTOPage(Page<User> userPage){
        return userPage.map(
                user -> userMapper.userToUserDTO(user));
    }


    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION, id)));
        return userMapper.userToUserDTO(user);
    }

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = getCurrentUser();
        if(user.getBuiltIn()){
            throw new Bad
        }
    }
}


