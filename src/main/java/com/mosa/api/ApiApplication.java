package com.mosa.api;

import com.mosa.api.model.Gender;
import com.mosa.api.model.Person;
import com.mosa.api.repo.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Year;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PersonRepository personRepo) {
		return args -> {
			personRepo.save(new Person(
					null,
					"John",
					LocalDate.of(2000, 2, 15),
					44,
					Gender.MALE,
					"John@gmail.com",
					"http://localhost:8080/person/photo/male.png"
			));
		};
	}
}
