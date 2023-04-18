package com.people.testowanie.oprogramowania.model.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import java.util.ArrayList;
import java.util.List;
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
public class PeopleResponse {

    @Builder.Default
    private List<PersonDto> people = new ArrayList<>();

    @JsonIgnore
    public static PeopleResponse from(List<PersonDto> source) {
        return PeopleResponse.builder()
            .people(source)
            .build();
    }
}


