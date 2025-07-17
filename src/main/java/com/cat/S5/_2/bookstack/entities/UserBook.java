package com.cat.S5._2.bookstack.entities;

import com.cat.S5._2.bookstack.enums.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_book_associations")
@Getter @Setter
public class UserBook {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Min(0) @Max(5)
    private int rating;

    @Column(length = 1000)
    private String comment;
}
