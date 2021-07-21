package br.com.cwi.apus.order.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
