package diginamic.lightRh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import diginamic.lightRh.exceptions.ConflictWithExistingAbsenceException;
import diginamic.lightRh.exceptions.InvalidDateRangeException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<String> handleInvalidDateRangeException(InvalidDateRangeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ConflictWithExistingAbsenceException.class)
    public ResponseEntity<String> handleConflictWithExistingAbsenceException(ConflictWithExistingAbsenceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create absence");
    }
}

