package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Instant startTime = Instant.now();
        final String reqeustUrl = request.getRequestURL().toString();
        final Locale locale = request.getLocale();
        final String method = request.getMethod();
        final String remoteHost = request.getRemoteHost();
        final String remoteUser = request.getRemoteUser();
        final String remoteAddr = request.getRemoteAddr();
        final int remotePort = request.getRemotePort();
        String body = null;

//        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        if("POST".equalsIgnoreCase(method)){
//            final Map<String, String[]> parameterMap = requestCacheWrapperObject.getParameterMap();
            body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        final Instant startTime = Instant.now();
//        final String reqeustUrl = request.getRequestURL().toString();
//        final Locale locale = request.getLocale();
//        final String method = request.getMethod();
//        final String remoteHost = request.getRemoteHost();
//        final String remoteUser = request.getRemoteUser();
//        final String remoteAddr = request.getRemoteAddr();
//        final int remotePort = request.getRemotePort();
//        String body = null;
//
//        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
//        if("POST".equalsIgnoreCase(method)){
//            final Map<String, String[]> parameterMap = requestCacheWrapperObject.getParameterMap();
//            body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        }


    }
}
