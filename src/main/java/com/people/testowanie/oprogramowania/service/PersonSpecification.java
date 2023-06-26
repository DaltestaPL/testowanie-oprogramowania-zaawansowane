package com.people.testowanie.oprogramowania.service;

import static java.util.Objects.nonNull;

import com.people.testowanie.oprogramowania.model.entity.PersonEntity;
import com.people.testowanie.oprogramowania.model.rest.request.PersonSearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class PersonSpecification implements Specification<PersonEntity> {

    private final PersonSearchRequest criteria;

    @Override
    public Predicate toPredicate(Root<PersonEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(personNameLike(root, criteriaBuilder),
            genderEquals(root, criteriaBuilder),
            weightFromLessThanOrEqualTo(root, criteriaBuilder),
            weightToGraterThanOrEqualTo(root, criteriaBuilder),
            birthdayLessThan(root, criteriaBuilder),
            birthdayGraterThan(root, criteriaBuilder));
    }

    private Predicate personNameLike(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getFullName()) ?
            criteriaBuilder.like(criteriaBuilder.lower(criteriaBuilder.concat(root.get("name"),
                criteriaBuilder.concat(" ", root.get("surname")))), "%" + criteria.getFullName().toLowerCase() + "%") :
            alwaysTruePredicate(criteriaBuilder);
    }
    private Predicate genderEquals(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getGender()) ?
            criteriaBuilder.equal(criteriaBuilder.lower(root.get("gender")), criteria.getGender().toLowerCase()) :
            alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate weightFromLessThanOrEqualTo(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getWeightFrom()) ?
            criteriaBuilder.greaterThanOrEqualTo(root.get("weight"), criteria.getWeightFrom()) :
            alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate weightToGraterThanOrEqualTo(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getWeightTo()) ?
            criteriaBuilder.lessThanOrEqualTo(root.get("weight"), criteria.getWeightTo()) :
            alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate birthdayGraterThan(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getBirthdayFrom()) ?
            criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), criteria.getBirthdayFrom()) :
            alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate birthdayLessThan(Root<PersonEntity> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteria.getBirthdayTo()) ?
            criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), criteria.getBirthdayFrom()) :
            alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
