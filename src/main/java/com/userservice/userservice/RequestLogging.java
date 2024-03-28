package com.userservice.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLogging implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogging.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("Request : {}", request.getMethod() +" "+ request.getRequestURL() +" "+ request.getParameterMap());
        return true;
    }
}
