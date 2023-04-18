package com.people.testowanie.oprogramowania.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.people.testowanie.oprogramowania.model.entity.PersonEntity;
import com.people.testowanie.oprogramowania.model.enums.GenderEnum;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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
public class PersonDto {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthday;

    private GenderEnum gender;

    private Integer weight;

    @JsonIgnore
    public static PersonDto from(PersonEntity source) {
        return PersonDto.builder()
            .id(source.getId())
            .name(source.getName())
            .surname(source.getSurname())
            .birthday(source.getBirthday())
            .gender(source.getGender())
            .weight(source.getWeight())
            .build();
    }
}


