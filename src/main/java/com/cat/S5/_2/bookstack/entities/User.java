package com.cat.S5._2.bookstack.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long userId;
    String userName;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>(); // Un Set garantit l'unicité (un même rôle ne peut pas être ajouté deux fois à un utilisateur).
                                                // HashSet est optimisé pour les recherches rapides (contains())
                                                //  new HashSet<>() : Évite les NullPointerException si aucun rôle n'est attribué.

}
