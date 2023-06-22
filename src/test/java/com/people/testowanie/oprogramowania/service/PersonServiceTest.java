package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import com.people.testowanie.oprogramowania.model.entity.PersonEntity;
import com.people.testowanie.oprogramowania.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        var personDto = PersonDto.builder().name("Name 1").surname("Surname 1").build();
        var personEntity = PersonEntity.builder().id(1L).name("Name 1").surname("Surname 1").build();
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
}
