package mk.ukim.finki.emt.lab1_groupb_emt.web.handler;

import mk.ukim.finki.emt.lab1_groupb_emt.model.exception.HostNotFoundException;
import mk.ukim.finki.emt.lab1_groupb_emt.web.controller.AccommodationController;
import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AccommodationController.class)
public class AccommodationControllerExceptionHandler {
    @ExceptionHandler(HostNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(HostNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}
