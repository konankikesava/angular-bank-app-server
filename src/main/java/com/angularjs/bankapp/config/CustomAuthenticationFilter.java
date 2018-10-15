package com.angularjs.bankapp.config;

import com.angularjs.bankapp.controller.UserController;
import com.angularjs.bankapp.restmodels.LoginRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER =
            Logger.getLogger(CustomAuthenticationFilter.class.getName());

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        try {
            LoginRequest loginRequest = LoginRequest.getLoginRequest(request);
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    );
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            response.addHeader("P3P", "CP=\"Temporary\"");
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException | IOException e) {
            LOGGER.log(Level.WARNING, "Invalid or bad data {0}", e.toString());
            throw new InternalAuthenticationServiceException(
                    "Invalid or bad data", e);
        }
    }

}
