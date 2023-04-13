package org.movielist.service;

import org.movielist.domain.Role;
import org.movielist.domain.enums.RoleType;
import org.movielist.exception.ResourceNotFoundException;
import org.movielist.exception.message.ErrorMessage;
import org.movielist.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByType(RoleType roleType){
        return roleRepository.findByType(roleType).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION, roleType.name())));

    }


}
