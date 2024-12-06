package org.example.standalone.util.throttling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrottlingExceptionProvider implements
        ExceptionMapper<ThrottlingException> {
    @Override
    public Response toResponse(ThrottlingException e) {
        return
                Response.status(Response.Status.BAD_REQUEST).
                        entity(e.getMessage()).
                        type(MediaType.APPLICATION_JSON).
                        build();
    }
}
