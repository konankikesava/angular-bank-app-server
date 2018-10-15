/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularjs.bankapp.config;

import com.angularjs.bankapp.settings.Constants;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author ravindra.palli
 */
public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER =
            Logger.getLogger(SessionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        LOGGER.info("********* Session is created **********");
        event.getSession()
                .setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        LOGGER.info("<<<<<<<<< Session is destroyed >>>>>>>>>>");
    }
}
