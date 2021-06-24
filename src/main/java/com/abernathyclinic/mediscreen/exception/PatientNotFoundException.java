package com.abernathyclinic.mediscreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
    private final String error;

    public PatientNotFoundException(String error) {
        super(error);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
