package org.example.standalone.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.standalone.exceptions.ValidationErrorFault")
@Getter
@AllArgsConstructor
public class ValidationError extends Exception {
     private final ValidationErrorFault faultInfo;

     public ValidationError(final String message, final ValidationErrorFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public ValidationError(final String message, final ValidationErrorFault faultInfo, final Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public ValidationError(final String field) {
        super(Errors.NOT_EMPTY);
        this.faultInfo = ValidationErrorFault.builder().field(field).build();
    }

    public ValidationError(final String field, final String message) {
        super(Errors.NOT_EMPTY);
        this.faultInfo = ValidationErrorFault.builder().
                field(field).
                message(message).
                build();
    }

}
