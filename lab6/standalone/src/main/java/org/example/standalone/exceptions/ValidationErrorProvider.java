package org.example.standalone.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationErrorProvider implements ExceptionMapper<ValidationError> {
    @Override
    public Response toResponse(ValidationError e) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity(e.getMessage()).
                type(MediaType.APPLICATION_JSON).
                build();
    }
}
