package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {

    @RequestMapping("data")
    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "15");
        map.put("content", "Hello Gaaaaaaaaaaaaaad!");

        System.out.println(map);

        return map;
    }

}
