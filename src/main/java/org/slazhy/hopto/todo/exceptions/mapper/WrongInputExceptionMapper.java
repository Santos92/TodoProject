package org.slazhy.hopto.todo.exceptions.mapper;

import org.slazhy.hopto.todo.exceptions.DataNotFoundException;
import org.slazhy.hopto.todo.exceptions.WrongInputException;
import org.slazhy.hopto.todo.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public final class WrongInputExceptionMapper implements ExceptionMapper<WrongInputException> {
    @Override
    public Response toResponse(WrongInputException e) {
        ErrorMessage errorMessage = new ErrorMessage(400, e.getMessage());
        return Response.status(BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }
}
