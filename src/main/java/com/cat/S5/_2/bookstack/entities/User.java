package com.cat.S5._2.bookstack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table (name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50, message = "User name cannot exceed 50 characters")
    private String userName;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Email(message = "Enter a valid email")
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8, max = 100, message = "Password length must be 8-100 characters")
    private String password;


    @Builder.Default // sans cette  Lombok ignore l'initialisation = new HashSet<>() lors de l'utilisation du pattern Builder, ce qui pourrait causer des NullPointerException.
    // Nécessaire pour toutes les collections/champs initialisés quand on utilise @Builder
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",   // Évite la génération automatique d'une table par Hibernate (contrôle explicite du schéma)
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(); // Un Set garantit l'unicité (un même rôle ne peut pas être ajouté deux fois à un utilisateur).
                                                // HashSet est optimisé pour les recherches rapides (contains())
                                                //  new HashSet<>() : Évite les NullPointerException si aucun rôle n'est attribué.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBook> userBooksList = new ArrayList<>();

    public void addRole(Role role){ //on add le role au user et dans role, on note que le user a tel role
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public Long getId() {  // (dans updatePassword : #id == principal : fait appel à une methode getId pour avoir l'id)
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .toList(); // Utilisation de Stream API pour transformer les rôles en autorités
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User other)) return false;
        return Objects.equals(email, other.email);   // ou id si déjà présent
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
