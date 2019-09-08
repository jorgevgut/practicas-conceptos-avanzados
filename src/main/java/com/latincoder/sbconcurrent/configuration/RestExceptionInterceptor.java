package com.latincoder.sbconcurrent.configuration;

import com.google.common.collect.ImmutableMap;
import com.latincoder.sbconcurrent.model.exceptions.SupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionInterceptor {

    /**
     * This ExceptionHandler will catch the exceptions throwned (hence receives {@link Throwable})
     * from all RestController's, including {@link com.latincoder.sbconcurrent.controllers.Students}
     * Then verifies if this throwable is in fact an Exception Supported by this application, for
     * a list of SupportedExceptions see {@link SupportedException}
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Map<Object, Object>> processSupportedExceptions(Throwable throwable) {
        Optional<ResponseEntity<Map<Object, Object>>> supportedException = Arrays.stream(SupportedException.values())
                .filter(supportedEx -> hasSameClass(supportedEx, throwable))
                .map(this::createResponseEntityFromException)
                .findFirst();

        // return the matching supportedException if present, if not, respond with INTERNAL_SERVER_ERROR
        return supportedException.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    // In this section we keep private methods to put more complex logic that will make the
    // processSupportedExceptions more difficult to read

    /**
     * Evaluates that a {@link SupportedException} has the same class as the provided Throwable
     * @param exception
     * @param throwable
     * @return Boolean
     */
    private Boolean hasSameClass(SupportedException exception, Throwable throwable) {
        return throwable.getClass().equals(exception.getExceptionClass());
    }

    private ResponseEntity<Map<Object, Object>> createResponseEntityFromException(SupportedException exception){
        Objects.requireNonNull(exception);
        return new ResponseEntity<>(ImmutableMap.builder()
                .put("status", exception.getStatus().value())
                .put("error", exception.getStatus().getReasonPhrase())
                .build(),
                exception.getStatus());
    }
}
