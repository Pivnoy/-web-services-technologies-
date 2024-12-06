package org.example.standalone.util.throttling;

public class ThrottlingException extends Exception {
    private static final long serialVersionUID = 1L;
    public ThrottlingException() {
        super("requests rate limit");
    }
}
