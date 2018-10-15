package com.angularjs.bankapp.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER
            = Logger.getLogger(RestAuthenticationEntryPoint.class.getName());

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException
    ) throws IOException {
        LOGGER.info("commence: Rest authentication entry point");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print("{\"message\":\"Unauthorized\"}");
        response.getWriter().flush();
    }

}
