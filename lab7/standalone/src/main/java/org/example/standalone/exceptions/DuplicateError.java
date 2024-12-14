package org.example.standalone.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.standalone.exceptions.DuplicateErrorFault")
@Getter
@AllArgsConstructor
public class DuplicateError extends Exception{
    private final DuplicateErrorFault faultInfo;

    public DuplicateError(final String message, final DuplicateErrorFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public DuplicateError(final String message, final DuplicateErrorFault faultInfo, final Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public DuplicateError(final String field) {
        super(Errors.DUPLICATE);
        this.faultInfo = DuplicateErrorFault.builder().field(field).build();
    }
}
