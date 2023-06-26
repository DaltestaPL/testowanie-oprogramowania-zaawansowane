package com.people.testowanie.oprogramowania;

import com.people.testowanie.oprogramowania.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateBaseIT {

    @LocalServerPort
    int port;

    protected String baseUrl;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected PersonRepository personRepository;

    @BeforeEach
    void setup() {
        this.baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void cleanUp() {
        personRepository.deleteAll();
    }
}
