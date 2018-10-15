package com.angularjs.bankapp.exceptions;

import com.angularjs.bankapp.settings.Constants;
import com.angularjs.bankapp.shared.SimpleResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravindra.palli
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER =
            Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse handleUnauthorized(Exception e) {
        LOGGER.log(Level.INFO, "handleUnauthorized: {0}", e.toString());
        return new SimpleResponse(Constants.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse handleBadRequest(Exception e) {
        LOGGER.log(Level.INFO, "handleBadRequest: {0}", e.toString());
        return new SimpleResponse(Constants.BAD_REQUEST);
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse handleMissingSession(Exception e) {
        LOGGER.log(Level.INFO, "handleMissingSession: {0}", e.toString());
        return new SimpleResponse(Constants.MISSING_SESSION);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse handleMethodNotSupported(Exception e) {
        LOGGER.log(Level.INFO, "handleMethodNotSupported: {0}", e.toString());
        return new SimpleResponse(Constants.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleResponse handleServerError(Exception e) {
        LOGGER.log(Level.INFO, "handleServerError: {0}", e.toString());
        return new SimpleResponse(Constants.SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleResponse handleException(Exception e) {
        LOGGER.log(Level.INFO, "handleException: {0}", e.toString());
        return new SimpleResponse(Constants.EXCEPTION);
    }
}
