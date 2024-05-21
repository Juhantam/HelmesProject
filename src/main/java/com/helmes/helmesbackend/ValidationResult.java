package com.helmes.helmesbackend;

import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class ValidationResult {
    Set<ValidationErrorCode> validationErrorCodes;

    private ValidationResult(Set<ValidationErrorCode> errors) {
        this.validationErrorCodes = errors;
    }

    public static ValidationResult of() {
        return of(Set.of());
    }

    public static ValidationResult of(ValidationErrorCode errorCode) {
        return of(Set.of(errorCode));
    }

    public void orThrow() {
        if (!validationErrorCodes.isEmpty()) {
            throw ValidationException.of(validationErrorCodes);
        }
    }

}
