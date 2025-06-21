package com.raio_be.raio_be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(CategoryHasReverbsException.class)
    public ResponseEntity<ApiError> handleCategoryHasReverbs(CategoryHasReverbsException ex) {  
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }
}

