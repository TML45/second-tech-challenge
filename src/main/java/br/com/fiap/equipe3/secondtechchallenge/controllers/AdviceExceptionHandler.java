package br.com.fiap.equipe3.secondtechchallenge.controllers;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.InvalidContractingHoursException;
import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.NotFoundException;
import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.StandardError;
import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.ValidateError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@ControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<StandardError> handleNotFound(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        final StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(status.name());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ValidateError validateError = new ValidateError(
                Instant.now(),
                status.value(),
                status.name(),
                e.getBody().getDetail(),
                request.getRequestURI()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            validateError.addMessages(f.getField(), f.getDefaultMessage());
        }

        if (e.getBindingResult().getFieldErrors().isEmpty()) {
            for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                validateError.addMessages(objectError.getObjectName(), objectError.getDefaultMessage());
            }
        }

        return ResponseEntity.status(status).body(validateError);
    }

    @ExceptionHandler(InvalidContractingHoursException.class)
    public ResponseEntity<StandardError> handleInvalidContractingHoursException(
            InvalidContractingHoursException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        final StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(status.name());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
