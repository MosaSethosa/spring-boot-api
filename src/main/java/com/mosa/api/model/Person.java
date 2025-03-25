package com.mosa.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String name;

    @Past(message = "Date of Birth must be a date in the past")
    private LocalDate dateOfBirth;

    @Column(nullable = true)               // actual age to be calculated in the setter
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    @NotNull(message = "Email is mandatory")
    private String email;

    private String photoUrl;

    // Setter
    public void setAge(int age) {
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
