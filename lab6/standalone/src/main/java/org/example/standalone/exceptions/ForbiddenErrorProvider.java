package org.example.standalone.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenErrorProvider   implements ExceptionMapper<ForbiddenError> {
    @Override
    public Response toResponse(ForbiddenError e) {
        return
                Response.status(Response.Status.FORBIDDEN).
                        entity(e.getMessage()).
                        type(MediaType.APPLICATION_JSON).
                        build();
    }
}
