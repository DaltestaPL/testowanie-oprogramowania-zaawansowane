package com.people.testowanie.oprogramowania.model.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.people.testowanie.oprogramowania.model.dto.PersonDto;
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
public class SavePersonRequest {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Surname is required")
    private String surname;

    private LocalDate birthday;

    private GenderEnum gender;

    private Integer weight;

    @JsonIgnore
    public static PersonDto toDto(SavePersonRequest source) {
        return PersonDto.builder()
            .name(source.getName())
            .surname(source.getSurname())
            .birthday(source.getBirthday())
            .gender(source.getGender())
            .weight(source.getWeight())
            .build();
    }
}


