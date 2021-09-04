package com.github.phongnt.train.error;

import com.github.phongnt.train.dto.GenericResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Demo exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Custom handle not found response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponse> customHandleNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericResponse(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NonNull NoHandlerFoundException ex,
                                                                   @NonNull HttpHeaders headers,
                                                                   @NonNull HttpStatus status,
                                                                   @NonNull WebRequest request) {
        return new ResponseEntity<>(new GenericResponse("invalid endpoint", null),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Custom handle invalid parameter.
     *
     * @param ex the exception
     * @return the response entity
     */
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<GenericResponse> customHandleInvalidParameter(Exception ex) {
        return new ResponseEntity<>(new GenericResponse("invalid endpoint", null),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(@NonNull TypeMismatchException ex,
                                                        @NonNull HttpHeaders headers,
                                                        @NonNull HttpStatus status,
                                                        @NonNull WebRequest request) {
        return new ResponseEntity<>(new GenericResponse("invalid endpoint", null),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

}
