package com.example.finalwork02.controller;

import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {
    @PostMapping
    public Map<String,String> getSystemNotice(){
        HashMap<String, String> map = new HashMap<>();
        map.put("SystemNotice","helloworld");
        return map;
    }

}
