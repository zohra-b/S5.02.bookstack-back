package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring") // le mapper est directement injectable par Spring.
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(toRoleNames(user.getRoles()))") //
    UserDto toUserDto(User user); // seul le champ "role" est traité separement grace a @Mapping car c'est un set

    @Mapping(target = "roles", expression = "java(toRoleEntities(userDto.roles()))")
    User toEntity(UserDto userDto);

    //HELPERS
    default Set<String> toRoleNames(Set<UserRole> roles) {
        if (roles == null) {
            return Set.of();
        }
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }


    default Set<UserRole> mapUserDtoRolesToUserRoles(Set<String> roleNames){
        if (roleNames == null){
            return Set.of();
        }

        return roleNames.stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }


}
