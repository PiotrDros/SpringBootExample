package com.example.cache;

import com.example.cache.CacheService.CachedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    private CacheService service;

    @RequestMapping("/cache/{name}")
    public CachedValue getCachedValue(@PathVariable String name) {
        return service.getCachedValue(name);
    }

}
