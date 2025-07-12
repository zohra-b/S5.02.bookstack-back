package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring") // le mapper est directement injectable par Spring.
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapUserRolesToStrings(user.getRoles()))") //
        // Spécifie comment mapper le champ roles de User vers UserDto : on cree une methode UserRoles toStrings

    UserDto toUserDto(User user); // seul le champ "role" est traité separement grace a @Mapping car c'est un set

    default Set<String> mapUserRolesToStrings(Set<UserRole> roles) {
        if (roles == null) {
            return Set.of();
        }
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.toSet());
    }
}
