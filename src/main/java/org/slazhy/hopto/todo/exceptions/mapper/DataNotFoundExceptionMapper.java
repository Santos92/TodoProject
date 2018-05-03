package org.slazhy.hopto.todo.exceptions.mapper;

import org.slazhy.hopto.todo.exceptions.DataNotFoundException;
import org.slazhy.hopto.todo.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public final class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(404, e.getMessage());
        return Response.status(NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
