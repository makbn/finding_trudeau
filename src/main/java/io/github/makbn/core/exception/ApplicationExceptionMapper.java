package io.github.makbn.core.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(ApplicationException e) {
        return Response.serverError().build();
    }
}
