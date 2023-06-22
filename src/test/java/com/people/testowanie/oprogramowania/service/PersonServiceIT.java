package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.exception.exceptions.EntityNotFoundException;
import com.people.testowanie.oprogramowania.repository.PersonRepository;
import com.people.testowanie.oprogramowania.utils.PersonFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceIT {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    void cleanUp() {
        personRepository.deleteAll();
    }

    @Test
    void whenFindAllPeople_thenFindAllPeopleCorrectly() {
        //given
        var peopleCollection = PersonFactory.personCollection();
        personRepository.saveAll(peopleCollection);

        //when
        var result = personService.findAllPeople();

        //then
        assertEquals(peopleCollection.size(), result.size());
    }

    @Test
    void whenDeletePersonById_thenPersonCorrectlyRemoved() {
        //given
        var person = personRepository.save(PersonFactory.simplePersonEntity().build());
        //and
        assertTrue(personRepository.existsById(person.getId()));

        //when
        personService.deleteById(person.getId());

        //then
        assertFalse(personRepository.existsById(person.getId()));
    }

    @Test
    void whenDeletePersonById_thenShouldThrowException() {
        //given
        var notExistPersonId = 1000000L;

        //when && then
        assertThrows(EntityNotFoundException.class, () -> personService.deleteById(notExistPersonId));
    }
}
