package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter loggingFilter(){
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA:");

        return filter;
    }

}
