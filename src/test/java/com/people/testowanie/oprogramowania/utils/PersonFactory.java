package com.people.testowanie.oprogramowania.utils;

import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import com.people.testowanie.oprogramowania.model.entity.PersonEntity;
import com.people.testowanie.oprogramowania.model.enums.GenderEnum;

import java.time.LocalDate;
import java.util.List;

public class PersonFactory {

    public static List<PersonEntity> personCollection() {
        return List.of(
                simplePersonEntity().build(),
                simplePersonEntity().name("Katarzyna").surname("Janowska").gender(GenderEnum.FEMALE).build(),
                simplePersonEntity().name("Janina").surname("Adamska").gender(GenderEnum.FEMALE).build());
    }

    public static PersonEntity.PersonEntityBuilder simplePersonEntity() {
        return PersonEntity.builder()
                .name("Jan")
                .surname("Kowalski")
                .birthday(LocalDate.of(2000, 4, 13))
                .gender(GenderEnum.MALE)
                .weight(79);
    }
    public static PersonDto.PersonDtoBuilder simplePersonDto() {
        return PersonDto.builder()
                .name("Jan")
                .surname("Kowalski")
                .birthday(LocalDate.of(2000, 4, 13))
                .gender(GenderEnum.MALE)
                .weight(79);
    }
}
