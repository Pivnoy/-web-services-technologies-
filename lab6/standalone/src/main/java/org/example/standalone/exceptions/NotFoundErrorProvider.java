package org.example.standalone.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundErrorProvider implements ExceptionMapper<NotFoundError> {
    @Override
    public Response toResponse(NotFoundError e) {
        return Response.status(Response.Status.NOT_FOUND).
                entity(e.getMessage()).
                 type(MediaType.APPLICATION_JSON).
                build();
    }
}

