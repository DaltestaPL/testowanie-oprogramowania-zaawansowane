package com.people.testowanie.oprogramowania.repository;

import com.people.testowanie.oprogramowania.utils.PersonFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    /*
    Uwaga!
    W normalnym przypadku nie testujemy metod w JpaRepository.
    Testujemy jedynie napisane przez siebie metody w PersonRepository
    Ten test ma jedynie wyjaśnić sposób testowania repozytoriów
     */
    @Test
    void whenFindAll_thenShouldFindAllPeople() {
        //given
        var expectedSize = 1;
        testEntityManager.persist(PersonFactory.simplePersonEntity().build());

        //when
        var result = personRepository.findAll();

        //then
        assertNotNull(result);
        //and
        assertEquals(expectedSize, result.size());
    }
}
