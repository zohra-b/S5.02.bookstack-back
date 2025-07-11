package com.cat.S5._2.bookstack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "books")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100, message = "The title's max length is 100 characters")
    private String title;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50, message = "The author's max length is 50 characters")
    private String author;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column (unique = true, nullable = false)
    @NotNull
    private String isbn;
}
