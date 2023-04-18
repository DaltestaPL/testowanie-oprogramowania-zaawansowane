package com.people.testowanie.oprogramowania.model.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private PersonDto person;

    @JsonIgnore
    public static PersonResponse from(PersonDto source) {
        return PersonResponse.builder()
            .person(source)
            .build();
    }
}


