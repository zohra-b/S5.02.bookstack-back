package com.cat.S5._2.bookstack.entities;

import com.cat.S5._2.bookstack.enums.UserRole;
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
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true)
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.ROLE_USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBook> userBooksList = new ArrayList<>();

    public void toggleRole() {
        this.role = this.role == UserRole.ROLE_USER
                ? UserRole.ROLE_ADMIN
                : UserRole.ROLE_USER;
    }

    public Long getId() {  // (dans updatePassword : #id == principal : fait appel à une methode getId pour avoir l'id)
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    public boolean isAdmin() {
        return this.role == UserRole.ROLE_ADMIN;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getUserName() { // il faut le garder sinon, ne renvoie pas userName dans Insomnia/Postman
        return userName;
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
