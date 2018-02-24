package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {

    public static final String THREAD = "/thread";

    public static class ThreadInfo {
        public ThreadInfo() {

        }

        public long id = -1;

        public ThreadInfo(Long id) {
            this.id = id;
        }
    }
    private static Logger LOG = LoggerFactory.getLogger(DataController.class);

    @RequestMapping("data")
    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "15");
        map.put("content", "Hello Gaaaaaaaaaaaaaad!");

        LOG.info("Data was called");
        LOG.debug("Deubug data");
        LOG.warn("Warn data");

        return map;
    }

    @RequestMapping(THREAD)
    public ResponseEntity<ThreadInfo> thread() {
        Long id = Thread.currentThread().getId();
        return new ResponseEntity<ThreadInfo>(new ThreadInfo(id), HttpStatus.OK);
    }

}
