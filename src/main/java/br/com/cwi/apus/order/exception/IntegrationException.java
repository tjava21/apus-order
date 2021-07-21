package br.com.cwi.apus.order.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class IntegrationException extends RuntimeException {

    public IntegrationException(String message) {
        super("failed to perform " + message);
    }
}
