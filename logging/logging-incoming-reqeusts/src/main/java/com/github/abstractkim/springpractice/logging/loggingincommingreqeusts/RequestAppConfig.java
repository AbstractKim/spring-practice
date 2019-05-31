package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestAppConfig implements WebMvcConfigurer {

    private final CustomRequestInterceptor customRequestInterceptor;

    public RequestAppConfig(CustomRequestInterceptor customRequestInterceptor) {
        this.customRequestInterceptor = customRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customRequestInterceptor);
    }
}
