## Custom Rqeust Logging Using HandlerIntercepterAdater

참조 link
- https://www.baeldung.com/spring-http-logging
- https://www.javadevjournal.com/spring/log-incoming-requests-spring/

1. 스프링 부투 프로젝트 생성 (Web)
2. 레스트 컨트롤러 생성 (GET and POST)
3. TEST 코드 추가
4. HandlerInterceptorAdapter를 상속 받아 prehandle, afterCompletion을 Override한다
    - @Component 어노테이션을 붙인다
    - Bean 으로 등록
    - **여기서 로깅 로직 추가**
5. 설정파일을 생성하여 InterceptorRegistry에 4.에서 생성한 Interceptor를 등록
    - WebMvcConfigurer를 상속 받아 addInterceptoers를 Override한 후 Interceptor를 추가한다.
    

#### 문제점
HttpServletRequest 에서 body를 가져오려고 할때, Exception 발생 (NestedServletException)
- Request processing failed; nested exception is java.lang.IllegalStateException: Cannot call getInputStream() after getReader() has already been called for the current request
- reReader()한 후에, controller에서 HttpServletRequest로 부터 getInputStream()을 해서 Exception이 발생한 듯
- baeldung 페이지에서 'ContentCachingRequestWrapper'를 사용하는 방법을 알려줬으나.. 
    - Content-Type:application/x-www-form-urlencoded, Method-Type:POST 인 경우만 가능한 제약이 있음

Request body를 로깅하려고 할때는 다른 방법으로 해야 할듯함

###  Spring Built-in Request Logging

``` java
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
```

    logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG
    

