package org.movielist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.movielist.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private Boolean builtIn ;
    private Set<String> roles;

    public void setRoles(Set<Role> roles) {
        Set<String> strRoles = new HashSet<>();
        roles.forEach(role -> strRoles.add(role.getType().getName()));
        this.roles = strRoles;
    }
}
