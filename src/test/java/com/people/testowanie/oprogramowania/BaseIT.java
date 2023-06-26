package com.people.testowanie.oprogramowania;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.people.testowanie.oprogramowania.repository.PersonRepository;
import com.people.testowanie.oprogramowania.service.PersonService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class BaseIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected PersonService personService;

    @Autowired
    protected PersonRepository personRepository;

    @SneakyThrows
    protected <T> String toJson(T obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}