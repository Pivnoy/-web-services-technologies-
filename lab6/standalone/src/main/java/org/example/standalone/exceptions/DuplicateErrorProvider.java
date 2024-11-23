package org.example.standalone.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicateErrorProvider implements
        ExceptionMapper<DuplicateError> {
    @Override
    public Response toResponse(DuplicateError e) {
        return
                Response.status(Response.Status.BAD_REQUEST).
                        entity(e.getMessage()).
                        type(MediaType.APPLICATION_JSON).
                        build();
    }
}
