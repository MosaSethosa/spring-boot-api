package com.mosa.api.service.impl;

import com.mosa.api.model.Gender;
import com.mosa.api.model.Person;
import com.mosa.api.repo.PersonRepository;
import com.mosa.api.service.IPersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class PersonService implements IPersonService {

    private final PersonRepository personRepo;

    @Override
    public Person getPersonById(Long id) {
        return personRepo.findById(id).get();
    }

    @Override
    public Collection<Person> getAllPersons() {
        return personRepo.findAll();
    }

    @Override
    public Person createPerson(Person person) {
        person.setPhotoUrl(setPersonPhoto(person));
        return personRepo.save(person);
    }

    // helper method
    private String setPersonPhoto(Person person) {
        String strImage = "";
        if(person.getGender() == Gender.MALE) {
            strImage = "male.png";
        }
        else if(person.getGender() == Gender.FEMALE) {
            strImage = "female.png";
        }
        else {
            strImage = "other.png";
        }

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/person/photo/" + strImage)
                .toUriString();
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person personToUpdate = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Person id " + id + " not found"));

        //update variables
        if(!Objects.equals(person.getName(), personToUpdate.getName())) {
            personToUpdate.setName(person.getName());
        }

        if(!Objects.equals(person.getDateOfBirth(), personToUpdate.getDateOfBirth())) {
            personToUpdate.setDateOfBirth(person.getDateOfBirth());
        }

        if(!Objects.equals(person.getGender(), personToUpdate.getGender())) {
            personToUpdate.setGender(person.getGender());
            personToUpdate.setPhotoUrl(setPersonPhoto(person));
        }

        if(!Objects.equals(person.getEmail(), personToUpdate.getEmail())) {
            Optional<Person> personOptional = Optional.ofNullable(personRepo.findByEmail(person.getEmail()));
            if(personOptional.isPresent()) {
                throw new IllegalStateException("Email is taken!!!");
            }
            personToUpdate.setEmail(person.getEmail());
        }

        return personToUpdate;
    }

    @Override
    public Boolean deletePerson(Long id) {
        personRepo.deleteById(id);      // TODO :: check logic
        return true;
    }

}
