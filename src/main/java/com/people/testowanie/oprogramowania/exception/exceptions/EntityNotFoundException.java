package com.people.testowanie.oprogramowania.exception.exceptions;

import com.people.testowanie.oprogramowania.exception.messages.ExceptionMessages;

public class EntityNotFoundException extends RuntimeException {

    public <T> EntityNotFoundException(Class<T> clazz, Long id) {
        super(String.format(ExceptionMessages.ENTITY_FOR_PROVIDED_ID_NOT_FOUND.getMessage(), clazz.getName(), id));
    }
}
