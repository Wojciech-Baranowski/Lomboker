package com.simmondobber.lomboker.lombokize.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankClassCodeException extends LombokizeException {

    public BlankClassCodeException() {
        super("Class code is blank!");
    }
}
