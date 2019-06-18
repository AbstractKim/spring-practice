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


application.properties

    logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG
    


#### logback DBAppender Oracle table schema sample
[참조 https://github.com/qos-ch/logback/blob/master/logback-classic/src/main/resources/ch/qos/logback/classic/db/script/oracle.sql](https://github.com/qos-ch/logback/blob/master/logback-classic/src/main/resources/ch/qos/logback/classic/db/script/oracle.sql)

logging_event
```sql
CREATE TABLE logging_event 
  (
    timestmp         NUMBER(20) NOT NULL,
    formatted_message  VARCHAR2(4000) NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    reference_flag    SMALLINT,
    arg0              VARCHAR(254),
    arg1              VARCHAR(254),
    arg2              VARCHAR(254),
    arg3              VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL,
    event_id          NUMBER(10) PRIMARY KEY
  );
```

logging_event_id_seq_trig
```sql
CREATE TRIGGER logging_event_id_seq_trig
  BEFORE INSERT ON logging_event
  FOR EACH ROW  
  BEGIN  
    SELECT logging_event_id_seq.NEXTVAL 
    INTO   :NEW.event_id 
    FROM   DUAL;  
  END;
```

logging_event_property
```sql
CREATE TABLE logging_event_property
  (
    event_id	      NUMBER(10) NOT NULL,
    mapped_key        VARCHAR2(254) NOT NULL,
    mapped_value      VARCHAR2(1024),
    PRIMARY KEY(event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
```

logging_event_exception
```sql
 CREATE TABLE logging_event_exception
  (
    event_id         NUMBER(10) NOT NULL,
    i                SMALLINT NOT NULL,
    trace_line       VARCHAR2(254) NOT NULL,
    PRIMARY KEY(event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
  ```