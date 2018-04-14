package com.example.cache;

import com.example.CacheConfig;

import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheResult;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
public class CacheService {

    @CacheResult(cacheName = CacheConfig.CACHE_NAME)
    public CachedValue getCachedValue(String name) {

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new CachedValue(name);

    }

   

    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor
    static class CachedValue  implements Serializable{
        private static final long serialVersionUID = 6650399573317852330L;
        private String name;
        private String time;
        
        public CachedValue(String name) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.name = name;
            this.time = LocalDateTime.now().format(formatter);
        }

    }

}
