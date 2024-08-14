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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    @Size(min = 5, max = 25, message = "Username should have between 5 to 25 characters")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    @Size(min = 8, max = 25, message = "Password should have between 8 to 25 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$"
            , message = "Password must have an upper case and lowercase letter, numbers, special characters, and no space")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Column(columnDefinition = "DATETIME DEFAULT TIMESTAMP(CURRENT_DATE)")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Null(message = "Date is automatically calculated in the system, please don't enter a date")
    private final LocalDate registration_date = LocalDate.now(); ;
}
