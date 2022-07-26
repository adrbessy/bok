package com.perso.bok.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnfoundContentBlockException extends RuntimeException {

    public UnfoundContentBlockException(String s) {
        super(s);
    }

}
