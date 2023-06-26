package com.people.testowanie.oprogramowania.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.people.testowanie.oprogramowania.model.rest.request.SavePersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.people.testowanie.oprogramowania.controller.ApiConstraints.PERSON;
import static com.people.testowanie.oprogramowania.controller.PeopleController.SAVE_PERSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class PeopleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenSavePersonRequest_thenSavePersonCorrectly() throws Exception {
        //given
        String url = PERSON + SAVE_PERSON;
        //and
        String name = "Tomasz";
        String surname = "Wojtyra";
        var request = SavePersonRequest.builder().name(name).surname(surname).build();

        //when
        ObjectMapper objectMapper = new ObjectMapper();
        var requestJson = objectMapper.writeValueAsString(request);

        var result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestJson));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.person.name", is(name)))
                .andExpect(jsonPath("$.person.surname", is(surname)));
    }
}
