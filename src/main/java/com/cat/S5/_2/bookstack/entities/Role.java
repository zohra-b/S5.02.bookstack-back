package com.cat.S5._2.bookstack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity et pas auto car meilleure compatibilité avec mysql notamment pour INSERT
    private Long id;
    private String name; // "ROLE_USER", "ROLE_ADMIN"
}
