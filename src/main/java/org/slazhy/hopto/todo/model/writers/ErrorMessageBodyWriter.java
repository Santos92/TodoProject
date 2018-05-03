package org.slazhy.hopto.todo.model.writers;

import org.slazhy.hopto.todo.model.ErrorMessage;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static javax.ws.rs.core.MediaType.*;

@Provider
@Produces(TEXT_PLAIN)
public final class ErrorMessageBodyWriter implements MessageBodyWriter<ErrorMessage> {
    @Override
    public boolean isWriteable(Class<?> errorMessage, Type type, Annotation[] annotations, MediaType mediaType) {
        return ErrorMessage.class.isAssignableFrom(errorMessage);
    }

    @Override
    public void writeTo(ErrorMessage errorMessage, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(errorMessage.toString().getBytes());
    }
}
