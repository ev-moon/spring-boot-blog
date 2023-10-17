package com.evmoon.blog.web.exception;

public class UnauthorizedException extends RuntimeException {
    public final static String message = "Login required";

    public UnauthorizedException() {
        super(message);
    }
}
