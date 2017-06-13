package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String greeting(Map<String, String> model) {
        model.put("message", "Hello Gad!");

        System.out.println(model);
        return "index";
    }
}