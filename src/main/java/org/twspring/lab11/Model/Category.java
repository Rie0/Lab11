package org.twspring.lab11.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    @Size(min = 5, max = 25, message = "Name should have between 5 to 25 characters")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
