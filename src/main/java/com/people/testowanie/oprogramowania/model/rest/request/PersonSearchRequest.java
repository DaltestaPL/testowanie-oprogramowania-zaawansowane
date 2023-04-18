package com.people.testowanie.oprogramowania.model.rest.request;


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
public class PersonSearchRequest {

    private String fullName;

    private LocalDate birthdayFrom;

    private LocalDate birthdayTo;

    private String gender;

    private Integer weightFrom;

    private Integer weightTo;
}
