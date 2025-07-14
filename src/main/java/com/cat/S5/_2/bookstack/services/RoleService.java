package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;

    @Transactional
    public Role createRoleIfNotExists(UserRole roleName) {
        return roleRepo.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepo.save(newRole);
                });
    }

    public Role findByName(UserRole name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }


    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }
}