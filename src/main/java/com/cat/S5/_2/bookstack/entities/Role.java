package com.cat.S5._2.bookstack.entities;

import com.cat.S5._2.bookstack.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity et pas auto car meilleure compatibilité avec mysql notamment pour INSERT
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 20)
    private UserRole name = UserRole.ROLE_USER; // Valeur par défaut ROLE_USER,à modifier éventuellement

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
