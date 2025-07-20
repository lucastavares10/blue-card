package com.ltfreire.bluecard_api.infra.exceptions;

import com.ltfreire.bluecard_api.domain.exception.InvalidTokenException;
import com.ltfreire.bluecard_api.domain.exception.EmailAlreadyExistsException;
import com.ltfreire.bluecard_api.domain.utils.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> handleInvalidToken(InvalidTokenException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Token inválido", ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor", ex.getMessage(), request);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(Exception ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Email em uso", ex.getMessage(), request);
    }


    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String error, String message, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now().toString(),
                status.value(),
                error,
                message,
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(apiError, status);
    }
}
