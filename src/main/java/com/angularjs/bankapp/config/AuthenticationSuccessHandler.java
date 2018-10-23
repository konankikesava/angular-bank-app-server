package com.angularjs.bankapp.config;

import com.angularjs.bankapp.restmodels.UserResponse;
import com.angularjs.bankapp.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    IUserService userService;

    private static final Logger LOGGER
            = Logger.getLogger(AuthenticationSuccessHandler.class.getName());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        LOGGER.info("onAuthenticationSuccess: Successful authentication");
        try {
            // Get current logged in user details
            ObjectMapper mapper = new ObjectMapper();
            response.addHeader("Content-Type", "application/json;charset=UTF-8");

            response.getWriter().print(
                mapper.writeValueAsString(new UserResponse(
                    userService.findUserByUsername(authentication.getName())
                ))
            );
            response.getWriter().flush();

            SavedRequest savedRequest =
                   requestCache.getRequest(request, response);

            if (savedRequest == null) {
                clearAuthenticationAttributes(request);
                return;
            }
            String targetUrlParam = getTargetUrlParameter();
            if (isAlwaysUseDefaultTargetUrl() ||
                    (targetUrlParam != null &&
                            StringUtils.hasText(request.getParameter(targetUrlParam)))) {
                requestCache.removeRequest(request, response);
                clearAuthenticationAttributes(request);
                return;
            }
            clearAuthenticationAttributes(request);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING,
                    "onAuthenticationSuccess: Failed to process further",
                    e.toString()
            );
        }
    }

    @Override
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

}
