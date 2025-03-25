package com.mosa.api.service.impl;

import com.mosa.api.model.Gender;
import com.mosa.api.model.Person;
import com.mosa.api.repo.PersonRepository;
import com.mosa.api.service.IPersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Random;

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
        String photoNames[] = {"male.png", "female.", "other.png"};
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/person/photo/" + photoNames[new Random().nextInt(3)])
                .toUriString();
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepo.save(person); // TODO :: check logic
    }

    @Override
    public Boolean deletePerson(Long id) {
        personRepo.deleteById(id);      // TODO :: check logic
        return true;
    }

}
