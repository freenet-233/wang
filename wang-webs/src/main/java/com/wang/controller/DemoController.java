package com.wang.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Robin Wang
 * @description TODO
 * @date 2022-12-11 下午9:02
 */
@RestController
@RequestMapping("/api/v1/greetings")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from this server");
    }

    @GetMapping("/sayGoodBye")
    public ResponseEntity<String> sayGoodBye(String greeting) {
        return ResponseEntity.ok("Good bye from this server");
    }
}
