package org.twspring.lab11.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "User id cannot be empty")
    @Positive(message = "User Id cannot be a zero or a negative number")
    private Integer user_id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "Post id cannot be empty")
    @Positive(message = "Post Id cannot be a zero or a negative number")
    private Integer post_id;

    @Column(columnDefinition = "VARCHAR(150) NOT NULL")
    @Size(min = 10, max = 150, message = "Content must have between 10 to 150 characters")
    private String content;

    @Column(columnDefinition = "DATETIME DEFAULT TIMESTAMP(CURRENT_DATE)")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate comment_date  = LocalDate.now();

}
