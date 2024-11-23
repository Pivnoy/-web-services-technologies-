package org.example.standalone.exceptions;

import lombok.*;

@Getter
public class ValidationError extends Exception{
    private static final long serialVersionUID = 3L;
    public ValidationError(String message) {
        super(message + " " + Errors.VALIDATE);
    }

}