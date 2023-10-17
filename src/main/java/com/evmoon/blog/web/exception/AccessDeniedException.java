package com.evmoon.blog.web.exception;

public class AccessDeniedException extends RuntimeException {
    public final static String message = "Access denied";

    public AccessDeniedException() {
        super(message);
    }
}
