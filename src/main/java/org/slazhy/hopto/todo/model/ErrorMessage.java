package org.slazhy.hopto.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class ErrorMessage {

    private final int statusCode;
    private final String errorMessage;

    public ErrorMessage(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String toString(){
        return String.format("Status code: %s\nError message: %s",getStatusCode(),getErrorMessage());
    }

}
