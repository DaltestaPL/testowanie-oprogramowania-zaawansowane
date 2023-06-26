package com.people.testowanie.oprogramowania.controller;

import com.people.testowanie.oprogramowania.BaseIT;
import com.people.testowanie.oprogramowania.model.rest.request.PersonSearchRequest;
import com.people.testowanie.oprogramowania.model.rest.request.SavePersonRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static com.people.testowanie.oprogramowania.controller.ApiConstraints.PERSON;
import static com.people.testowanie.oprogramowania.controller.PeopleController.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PeopleControllerIT extends BaseIT {

    @Test
    @Sql(value = "/sql/people-init.sql")
    void whenFindPersonById_thenCorrectResponse() throws Exception {
        //given
        var personId = 1;
        String url = PERSON + "/" + personId;
        //and
        var name = "Jan";
        var surname = "Kowalski";

        //when
        var result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.person.id", is(personId)))
                .andExpect(jsonPath("$.person.name", is(name)))
                .andExpect(jsonPath("$.person.surname", is(surname)));
    }

    @Test
    void whenSavePersonRequest_thenSavePersonCorrectly() throws Exception {
        //given
        String url = PERSON + SAVE_PERSON;
        //and
        String name = "Tomasz";
        String surname = "Wojtyra";
        var request = SavePersonRequest.builder().name(name).surname(surname).build();

        //when
        var result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.person.name", is(name)))
                .andExpect(jsonPath("$.person.surname", is(surname)));
    }

    @Test
    void whenSavePersonWithoutNameRequest_thenBadRequest() throws Exception {
        //given
        var url = PERSON + SAVE_PERSON;
        var expectedStatus = HttpStatus.BAD_REQUEST;
        var expectedMessagesCount = 1;
        //and
        var surname = "Wojtyra";
        var request = SavePersonRequest.builder().name(null).surname(surname).build();

        //when
        var result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        //then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.error", is(expectedStatus.getReasonPhrase())))
                .andExpect(jsonPath("$.message.size()", is(expectedMessagesCount)));
    }

    @Test
    @Sql(value = "/sql/people-init.sql")
    void whenFindAllPeopleRequest_thenShouldFindPeopleCorrectly() throws Exception {
        //given
        var url = PERSON + FIND_ALL_PEOPLE;

        //when
        var result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.people.size()", is(5)));
    }

    @ParameterizedTest
    @MethodSource("searchByCriteriaParams")
    @Sql(value = "/sql/people-init.sql")
    void whenFindByCriteria_thenCorrectResponse(String fullName, Integer weightFrom, Integer expectedSize) throws Exception {
        //given
        var url = PERSON + FIND_PEOPLE_BY_CRITERIA;
        //and
        var request = PersonSearchRequest.builder()
                .fullName(fullName)
                .weightFrom(weightFrom)
                .build();

        //when
        var result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.people.size()", is(expectedSize)));
    }

    private static Stream<Arguments> searchByCriteriaParams() {
        return Stream.of(
                Arguments.of("Jan Kowalski", null, 1),
                Arguments.of("Kowalski", null, 2),
                Arguments.of(null, 70, 2),
                Arguments.of("Kowalski", 70, 1)
        );
    }
}
