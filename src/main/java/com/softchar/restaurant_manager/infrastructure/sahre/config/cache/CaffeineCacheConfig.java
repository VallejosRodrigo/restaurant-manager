package com.softchar.restaurant_manager.infrastructure.sahre.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Value("${cache.booking.ttl}")
    private long cacheBookingTtl;

    @Value("${cache.booking.max-size}")
    private long cacheBookingMaxSize;

    public static final String BOOKING_CACHE = "BOOKING_CACHE";

    public CacheManager cacheManager(){
        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(cacheBookingTtl, cacheBookingMaxSize));
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(caches);

        return manager;
    }

    private static CaffeineCache buildCache(Long ttl, long size){
        return new CaffeineCache(CaffeineCacheConfig.BOOKING_CACHE, Caffeine.newBuilder()
                .expireAfterWrite(ttl, TimeUnit.SECONDS)
                .maximumSize(size)
                .build());
    }

}
