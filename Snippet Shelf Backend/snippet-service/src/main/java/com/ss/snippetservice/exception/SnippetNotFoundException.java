package com.ss.snippetservice.exception;

public class SnippetNotFoundException extends RuntimeException {
    public SnippetNotFoundException(String message) {
        super(message);
    }

    public SnippetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}