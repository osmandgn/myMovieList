package org.movielist.mapper;

import org.mapstruct.Mapper;
import org.movielist.domain.User;
import org.movielist.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> map(List<User> userList);
}
