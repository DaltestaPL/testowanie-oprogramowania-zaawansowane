package com.people.testowanie.oprogramowania.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.people.testowanie.oprogramowania.BaseIT;
import com.people.testowanie.oprogramowania.model.rest.request.SavePersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.people.testowanie.oprogramowania.controller.ApiConstraints.PERSON;
import static com.people.testowanie.oprogramowania.controller.PeopleController.SAVE_PERSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PeopleControllerIT extends BaseIT {

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
}
