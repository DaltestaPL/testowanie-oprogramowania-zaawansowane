package com.people.testowanie.oprogramowania.service;

import com.people.testowanie.oprogramowania.exception.exceptions.EntityNotFoundException;
import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import com.people.testowanie.oprogramowania.model.entity.PersonEntity;
import com.people.testowanie.oprogramowania.model.rest.request.PersonSearchRequest;
import com.people.testowanie.oprogramowania.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public PersonDto findById(Long personId) {
        return PersonDto.from(repository.findById(personId)
            .orElseThrow(() -> new EntityNotFoundException(PersonEntity.class, personId)));
    }

    @Override
    public List<PersonDto> findAllPeople() {
        return repository.findAll().stream().map(PersonDto::from).collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findByCriteria(PersonSearchRequest criteria) {
        var specification = new PersonSpecification(criteria);
        return repository.findAll(specification).stream()
            .map(PersonDto::from).collect(Collectors.toList());
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        return PersonDto.from(repository.save(PersonEntity.toNewEntity(personDto)));
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        return PersonDto.from(repository.findById(personDto.getId()).map(personEntity -> {
            personEntity.setName(personDto.getName());
            personEntity.setSurname(personDto.getSurname());
            personEntity.setGender(personDto.getGender());
            personEntity.setBirthday(personDto.getBirthday());
            personEntity.setWeight(personDto.getWeight());
            return personEntity;
        }).orElseThrow(() -> new EntityNotFoundException(PersonEntity.class, personDto.getId())));
    }

    @Override
    public void deleteById(Long personId) {
        var person = repository.findById(personId)
            .orElseThrow(() -> new EntityNotFoundException(PersonEntity.class, personId));
        repository.delete(person);
    }
}
