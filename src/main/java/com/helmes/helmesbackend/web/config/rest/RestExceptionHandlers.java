package com.helmes.helmesbackend.web.config.rest;

import com.helmes.helmesbackend.ValidationException;
import com.helmes.helmesbackend.web.ValidationErrorsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandlers {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorsDto> handleAvotValidationException(ValidationException exception) {
        log.warn("Request produced validation errors: {}", exception.getErrors(), exception);
        HttpStatus highestStatusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(ValidationErrorsDto.ofErrorCodes(exception.getErrors()), highestStatusCode);
    }

}
