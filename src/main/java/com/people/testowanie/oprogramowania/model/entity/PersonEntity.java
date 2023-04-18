package com.people.testowanie.oprogramowania.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.people.testowanie.oprogramowania.model.dto.PersonDto;
import com.people.testowanie.oprogramowania.model.enums.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private Integer weight;

    @JsonIgnore
    public static PersonEntity toNewEntity(PersonDto source) {
        return PersonEntity.builder()
            .name(source.getName())
            .surname(source.getSurname())
            .birthday(source.getBirthday())
            .gender(source.getGender())
            .weight(source.getWeight())
            .build();
    }
}
