package com.cat.S5._2.bookstack.repositories;

import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByCreatedAtAfter(LocalDate date);
    List<User> findByNameContainingIgnoreCase(String keyword);
    List<User> findByRoleIn(List<Role> roles);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> searchByEmail(@Param("email") String email);
    @Query(value = "SELECT * FROM users WHERE created_at > ?1", nativeQuery = true)
    List<User> findRecentUsers(String date);


    //S save(S entity);
    //List<S> saveAll(Iterable<S> entities;
    // findById
    //findAll
    // getOne(Id id)
    // getReferenceById
    //existsById
    //count()
    // deleteById
    //delete(entity)
    //deleteAllById(iterable ids)
    //deleteAll()

}

