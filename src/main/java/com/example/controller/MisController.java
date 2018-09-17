package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MisController {


    @RequestMapping("/mis")
    public String greeting(Map<String, String> model) {
        model.put("message", "Miś jest wspaniały!");


        return "mis";
    }
}
