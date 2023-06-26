package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.repository.PersonRepository;
import com.people.testowanie.oprogramowania.utils.PersonFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void whenSavePerson_thenShouldSavePersonCorrectly() {
        //given
        var personDto = PersonFactory.simplePersonDto().build();
        var personEntity = PersonFactory.simplePersonEntity().build();
        //and
        when(personRepository.save(any())).thenReturn(personEntity);

        //when
        var result = personService.savePerson(personDto);

        //then
        assertNotNull(result);
        //and
        assertEquals(personEntity.getId(), result.getId());
        assertEquals(personEntity.getName(), result.getName());
        assertEquals(personEntity.getSurname(), result.getSurname());
    }

    @Test
    void whenFindPersonById_thenShouldFindPersonCorrectly() {
        //given
        var personId = 10L;
        var personEntity = Optional.of(PersonFactory.simplePersonEntity().id(personId).build());
        //and
        when(personRepository.findById(any())).thenReturn(personEntity);

        //when
        var result = personService.findById(personId);

        //then
        assertNotNull(result);
        //and
        assertEquals(personId, result.getId());
        assertEquals(personEntity.get().getName(), result.getName());
    }

    @Test
    void whenDeletePersonById_thenShouldDeletePersonCorrectly() {
        //given
        var personId = 1L;
        var personEntity = Optional.of(PersonFactory.simplePersonEntity().id(personId).build());
        //and
        when(personRepository.findById(any())).thenReturn(personEntity);

        //when
        personService.deleteById(personId);

//        Mockito.verify(personRepository.delete());
    }
}
