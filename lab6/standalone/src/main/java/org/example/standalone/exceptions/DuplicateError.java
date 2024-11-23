package org.example.standalone.exceptions;

import lombok.*;

@Getter
public class DuplicateError extends Exception {
    private static final long serialVersionUID = 1L;
    public DuplicateError(String field) {
        super(Errors.DUPLICATE + " " + field);
    }
}
