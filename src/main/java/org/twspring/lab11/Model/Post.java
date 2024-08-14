package org.twspring.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "Category id cannot be empty")
    @Positive(message = "Category Id cannot be a zero or a negative number")
    private Integer category_id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    @Size(min = 5, max = 25, message = "Title should have between 5 to 25 characters")
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @Column(columnDefinition = "VARCHAR(1500) NOT NULL")
    @Size(min = 50, max = 1500, message = "Content must have between 50 to 150 characters")
    private String content;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "User id cannot be empty")
    @Positive(message = "User Id cannot be a zero or a negative number")
    private Integer user_id;

    @Column(columnDefinition = "DATETIME DEFAULT TIMESTAMP(CURRENT_DATE)")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate publish_date = LocalDate.now();

}
