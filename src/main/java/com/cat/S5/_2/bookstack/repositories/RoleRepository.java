package com.cat.S5._2.bookstack.repositories;

import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.enums.UserRole;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRole name);
    boolean existsByName(UserRole name);
}
