package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.BaseIT;
import com.people.testowanie.oprogramowania.exception.exceptions.EntityNotFoundException;
import com.people.testowanie.oprogramowania.utils.PersonFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceIT extends BaseIT {

    @Autowired
    protected PersonService personService;

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
