package com.mosa.api.service;

import com.mosa.api.model.Person;

import java.util.Collection;

public interface IPersonService {


    Person getPersonById(Long id);
    Collection<Person> getAllPersons();
    Person createPerson(Person person);
    Person updatePerson(Long id, Person person);
    Boolean deletePerson(Long id);

}
