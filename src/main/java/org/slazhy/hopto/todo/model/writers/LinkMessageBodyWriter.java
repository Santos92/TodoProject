package org.slazhy.hopto.todo.model.writers;

import org.slazhy.hopto.todo.model.ErrorMessage;
import org.slazhy.hopto.todo.model.LinkMessage;

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

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Provider
@Produces(TEXT_PLAIN)
public final class LinkMessageBodyWriter implements MessageBodyWriter<LinkMessage> {
    @Override
    public boolean isWriteable(Class<?> linkMessage, Type type, Annotation[] annotations, MediaType mediaType) {
        return LinkMessage.class.isAssignableFrom(linkMessage);
    }

    @Override
    public void writeTo(LinkMessage linkMessage, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(linkMessage.toString().getBytes());
    }
}
