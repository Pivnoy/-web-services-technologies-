package org.example.standalone.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.standalone.exceptions.NotFoundErrorFault")
@Getter
@AllArgsConstructor
public class NotFoundError extends Exception {
    private final NotFoundErrorFault faultInfo;

    public NotFoundError(final String message, final NotFoundErrorFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public NotFoundError(final String message, final NotFoundErrorFault faultInfo, final Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public NotFoundError(final String entity) {
        super(Errors.NOT_FOUND);
        this.faultInfo = NotFoundErrorFault.builder().entity(entity).build();
    }

    public NotFoundError(final String entity, final String value) {
        super(Errors.NOT_FOUND);
        this.faultInfo = NotFoundErrorFault.builder().
                entity(entity).
                value(value).
                build();
    }

}
