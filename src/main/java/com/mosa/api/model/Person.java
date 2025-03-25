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
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    @NotNull(message = "Email is mandatory")
    private String email;

    private String photoUrl;

    public Person(Long id, String name, LocalDate dateOfBirth, int age, Gender gender, String email, String photoUrl) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    // Always return calculated age if dateOfBirth exists
    public Integer getAge() {
        if (dateOfBirth != null) {
            return Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        return age;
    }

}
