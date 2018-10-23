/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularjs.bankapp.config;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 *
 * @author ravindra.palli
 */
@Component
public class CORSFilter implements Filter {
    private static final Logger LOGGER =
            Logger.getLogger(CORSFilter.class.getName());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws ServletException, IOException {
        LOGGER.log(Level.INFO, "CORS Filter");

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader(
                "Access-Control-Allow-Origin", request.getHeader("Origin"));

        if (request.getHeader("Access-Control-Request-Method") != null &&
                "OPTIONS".equals(request.getMethod())) {
            LOGGER.info("OPTIONS headers are added");
            // CORS "pre-flight" request
            response.setHeader(
                    "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader(
                    "Access-Control-Allow-Headers",
                    "Content-Type, Authorization,"
                            + "x-requested-with, X-CSRF-TOKEN"
            );
            response.setHeader("Access-Control-Max-Age", "60");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
