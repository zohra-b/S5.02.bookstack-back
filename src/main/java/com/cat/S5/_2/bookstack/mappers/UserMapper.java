package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.dtos.user.UserSummaryDto;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring") // le mapper est directement injectable par Spring.
public interface UserMapper {

    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "role", source = "role", qualifiedByName = "roleToString")
    UserDto toUserDto(User user); // seul le champ "role" est traité separement grace a @Mapping car c'est un set

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    UserSummaryDto toSummaryDto(User user);

    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToRole")
    User toEntity(UserDto userDto);

    //HELPERS
    @Named("roleToString")
    default String roleToString(UserRole role) {
        return role != null ? role.name() : UserRole.ROLE_USER.name();
    }

    @Named("stringToRole")
    default UserRole stringToRole(String roleName) {
        return roleName != null ? UserRole.valueOf(roleName) : UserRole.ROLE_USER;
    }
}
