package com.innova.companymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException() {
        this("Unauthorized Access. You don't have privilege to perform this operation");
    }

    public UnauthorizedAccessException(String s) {
        super(s);
    }
}
