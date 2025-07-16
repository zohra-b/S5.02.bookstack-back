package com.cat.S5._2.bookstack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors",
        uniqueConstraints = @UniqueConstraint(columnNames = {"last_name", "first_name"}))
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long authorId;

    @Column(name = "last_name", length = 60)
    @NotBlank
    @Size(max = 60, message = "Last Name max length is 60 characters")
    String lastName;

    @Column(name = "first_name", length = 60)
    @Size(max = 60, message = "First Name max length is 60 characters")
    String firstName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
