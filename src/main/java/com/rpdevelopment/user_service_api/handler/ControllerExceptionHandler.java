package com.rpdevelopment.user_service_api.handler;

import com.rpdevelopment.user_service_api.dto.error.CustomErrorDto;
import com.rpdevelopment.user_service_api.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDto> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(), "Not Found", request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
