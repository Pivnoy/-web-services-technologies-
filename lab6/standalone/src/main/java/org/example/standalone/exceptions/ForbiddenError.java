package org.example.standalone.exceptions;

public class ForbiddenError extends Exception {
    private static final long serialVersionUID = 1L;
    public ForbiddenError() {
        super(Errors.FORBIDDEN);
    }
}
