package com.people.testowanie.oprogramowania.controller;

import com.people.testowanie.oprogramowania.RestTemplateBaseIT;
import com.people.testowanie.oprogramowania.model.rest.request.UpdatePersonRequest;
import com.people.testowanie.oprogramowania.model.rest.response.PersonResponse;
import com.people.testowanie.oprogramowania.utils.PersonFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static com.people.testowanie.oprogramowania.controller.ApiConstraints.PERSON;
import static com.people.testowanie.oprogramowania.controller.PeopleController.UPDATE_PERSON;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdatePersonTestIT extends RestTemplateBaseIT {

    @Test
    void whenUpdatePersonRequest_thenCorrectResponse() {
        //given
        var url = baseUrl +  PERSON + UPDATE_PERSON;
        var newName = "Tomasz";
        //and
        var person = personRepository.save(PersonFactory.simplePersonEntity().build());
        //and
        assertTrue(personRepository.existsById(person.getId()));

        var request = UpdatePersonRequest.builder()
                .id(person.getId())
                .name(newName)
                .gender(person.getGender())
                .weight(person.getWeight())
                .birthday(person.getBirthday())
                .surname(person.getSurname())
                .build();

        var entity = new HttpEntity<>(request);

        //when
        var result = restTemplate.exchange(url, HttpMethod.PUT, entity, PersonResponse.class);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        //and
        Assertions.assertNotNull(result.getBody());
        Assertions.assertNotNull(result.getBody().getPerson());
        //and
        Assertions.assertEquals(person.getId(), result.getBody().getPerson().getId());
        Assertions.assertEquals(newName, result.getBody().getPerson().getName());
    }
}
