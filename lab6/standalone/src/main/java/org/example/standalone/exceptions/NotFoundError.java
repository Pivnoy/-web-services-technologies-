package org.example.standalone.exceptions;

import lombok.*;

@Getter
public class NotFoundError extends Exception {
    private static final long serialVersionUID = 2L;
    public NotFoundError(String entity, String value) {
        super(Errors.NOT_FOUND + " field name: " + entity + " value: " + value);
    }

}
