package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import com.people.testowanie.oprogramowania.model.rest.request.PersonSearchRequest;
import java.util.List;

public interface PersonService {

    PersonDto findById(Long personId);

    List<PersonDto> findAllPeople();

    List<PersonDto> findByCriteria(PersonSearchRequest criteria);

    PersonDto savePerson(PersonDto personDto);

    PersonDto updatePerson(PersonDto personDto);

    void deleteById(Long personId);
}
