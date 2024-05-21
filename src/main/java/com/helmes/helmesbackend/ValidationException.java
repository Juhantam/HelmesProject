package com.helmes.helmesbackend;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException {
    Set<ValidationErrorCode> errors;

    private ValidationException(Set<ValidationErrorCode> errors) {
        super("Error codes: " + errors.toString());
        this.errors = errors;
    }

    public static ValidationException of(ValidationErrorCode error) {
        return of(Set.of(error));
    }
}
