package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingDemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/event")
    public ResponseEntity event(@RequestBody EventDto event){
        event.setId(10L);
        event.setTitle("saved!!");
        return ResponseEntity.ok(event);
    }
}
