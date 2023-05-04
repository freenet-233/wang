package com.wang.controller;

import com.wang.pojo.BaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 */
@RestController
@RequestMapping("/claim")
@RequiredArgsConstructor
@Slf4j
public class ClaimController {

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> claim(BaseRequest request) {
        log.info(request.toString());
        Map<String, Object> response = new HashMap<>();
        response.put("first", "11");
        response.put("size", 100);
        return ResponseEntity.ok(response);
    }
}
