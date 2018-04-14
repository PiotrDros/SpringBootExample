package com.example;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;

import java.util.concurrent.TimeUnit;

/**
 * A class responsible by creating cache instances
 */
@Component
public class CacheConfig implements JCacheManagerCustomizer {

    public static final String CACHE_NAME = "cacheName";

    @Override
    public void customize(CacheManager cacheManager) {
        //@formatter:off
        cacheManager.createCache(CACHE_NAME,Eh107Configuration.fromEhcacheCacheConfiguration(
                // Spring cache is not typed, so we use Object, Object
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).heap(100, EntryUnit.ENTRIES)
                )
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(60, TimeUnit.SECONDS)))
        ));
        //@formatter:on
    }

}