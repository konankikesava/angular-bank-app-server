/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularjs.bankapp.settings;

/**
 *
 * @author ravindra.palli
 */
public abstract class Constants {
    public static final String APP_NAME = "BANK APP";

    // Session
    public static final int SESSION_MAX_INACTIVE_INTERVAL = 30*60; // 30 minutes

    // Validation
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
    public static final String SUCCESS = "SUCCESS";

    // Exception Messages
    public static final String EXCEPTION = "Global exception";
    public static final String UNAUTHORIZED = "Unauthorized: Access is denied";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String MISSING_SESSION =
            "Missing session: Session expired or not exist";
    public static final String METHOD_NOT_SUPPORTED = "Method not supported";
    public static final String SERVER_ERROR =
            "Server Error: internal server error occured";
}
