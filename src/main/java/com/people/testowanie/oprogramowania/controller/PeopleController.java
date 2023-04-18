package com.people.testowanie.oprogramowania.controller;

import static com.people.testowanie.oprogramowania.controller.ApiConstraints.PERSON;

import com.people.testowanie.oprogramowania.model.rest.request.PersonSearchRequest;
import com.people.testowanie.oprogramowania.model.rest.request.SavePersonRequest;
import com.people.testowanie.oprogramowania.model.rest.request.UpdatePersonRequest;
import com.people.testowanie.oprogramowania.model.rest.response.PeopleResponse;
import com.people.testowanie.oprogramowania.model.rest.response.PersonResponse;
import com.people.testowanie.oprogramowania.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PERSON)
@RequiredArgsConstructor
public class PeopleController {

    public final static String FIND_PERSON_BY_ID = "/{personId}";
    public final static String DELETE_PERSON_BY_ID = "/{personId}";
    public final static String FIND_ALL_PEOPLE = "/all";
    public final static String FIND_PEOPLE_BY_CRITERIA = "/search-by-criteria";
    public final static String SAVE_PERSON = "/save";
    public final static String UPDATE_PERSON = "/update";

    private final PersonService personService;

    @GetMapping(FIND_PERSON_BY_ID)
    public ResponseEntity<PersonResponse> findById(@PathVariable Long personId) {
        return ResponseEntity.ok(PersonResponse.from(personService.findById(personId)));
    }

    @GetMapping(FIND_ALL_PEOPLE)
    public ResponseEntity<PeopleResponse> findAllPeople() {
        return ResponseEntity.ok(PeopleResponse.from(personService.findAllPeople()));
    }

    @PostMapping(FIND_PEOPLE_BY_CRITERIA)
    public ResponseEntity<PeopleResponse> findPeopleByCriteria(@RequestBody PersonSearchRequest criteria) {
        return ResponseEntity.ok(PeopleResponse.from(personService.findByCriteria(criteria)));
    }

    @PostMapping(SAVE_PERSON)
    public ResponseEntity<PersonResponse> savePerson(@RequestBody @Valid SavePersonRequest person) {
        return ResponseEntity.ok(PersonResponse.from(personService.savePerson(SavePersonRequest.toDto(person))));
    }

    @PutMapping(UPDATE_PERSON)
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody UpdatePersonRequest person) {
        return ResponseEntity.ok(PersonResponse.from(personService.updatePerson(UpdatePersonRequest.toDto(person))));
    }

    @DeleteMapping(DELETE_PERSON_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@PathVariable Long personId) {
        personService.deleteById(personId);
    }
}
