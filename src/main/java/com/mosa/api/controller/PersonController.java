package com.mosa.api.controller;

import com.mosa.api.model.Person;
import com.mosa.api.model.Response;
import com.mosa.api.repo.PersonRepository;
import com.mosa.api.service.impl.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<Response> getPersons() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Persons", personService.getAllPersons()))
                        .message("Persons retrieved")
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getPersonById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Persons", personService.getPersonById(id)))
                        .message("Person " + id + " retrieved")
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Persons", personService.createPerson(person)))
                        .message("Person created")
                        .httpStatus(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletePerson(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Person deleted", personService.deletePerson(id)))
                        .message("Person deleted")
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
