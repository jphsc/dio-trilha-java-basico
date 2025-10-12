package com.santanderdio.springsecurity.spring_security.exception;

import org.springframework.security.access.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {
    
    private static final long serialVersionUID = 1L;

	public CustomAccessDeniedException(String msg) {
        super(msg);
    }
    
    public CustomAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
