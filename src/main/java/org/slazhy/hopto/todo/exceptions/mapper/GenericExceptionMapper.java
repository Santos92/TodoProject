package org.slazhy.hopto.todo.exceptions.mapper;

import org.slazhy.hopto.todo.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public final class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Server couldn't handle your request");
        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}
