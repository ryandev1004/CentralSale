package com.ryan.centralsale.mapper;

import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.dto.UserCreateDTO;
import com.ryan.centralsale.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
        UserProductMapper.class
        })
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target = "userId", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);
}
