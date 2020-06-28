package org.d11.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.d11.rest.util.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void exception(Exception e, WebRequest request) throws Exception {
        // Doesn't do anything now but we'll keep this here so we know where to put it
        // if we think of something.
        super.handleException(e, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public void notFoundException(Exception e, HttpServletResponse response) throws Exception {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}
