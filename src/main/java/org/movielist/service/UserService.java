package org.movielist.service;

import org.movielist.domain.User;
import org.movielist.exception.ResourceNotFoundException;
import org.movielist.exception.message.ErrorMessage;
import org.movielist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
       return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
    }
}
