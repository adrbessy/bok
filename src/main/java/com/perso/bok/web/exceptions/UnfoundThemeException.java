package com.perso.bok.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnfoundThemeException extends RuntimeException {
    public UnfoundThemeException(String s) {
        super(s);
    }
}
