package com.example.demoproject.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Caffeine Cache Konfiguratsiya
 *
 * Cache Strategiyasi:
 * 1. Categories - 10 minutlik TTL (ko'p o'qiladi, kam o'zgaradi)
 * 2. Products - 5 minutlik TTL (ko'p o'qiladi)
 * 3. User Info - 30 minutlik TTL (personal data)
 * 4. Search Results - 2 minutlik TTL (dinamik)
 * 5. Rate Limit Buckets - 1 soatlik TTL
 */
@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Spring Cache Manager - Declarative caching
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "categories",
                "products",
                "productsByCategory",
                "userInfo",
                "searchResults",
                "rateLimitBuckets"
        );

        cacheManager.setCaffeine(Caffeine.newBuilder()
                .recordStats());

        return cacheManager;
    }

    /**
     * Categories Cache - 10 minutlik TTL
     * Foydalanish: @Cacheable(value = "categories", key = "#root.methodName")
     */
    @Bean
    public Cache<String, Object> categoriesCache() {
        return Caffeine.newBuilder()
                .maximumSize(1_000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    /**
     * Products Cache - 5 minutlik TTL
     * Foydalanish: @Cacheable(value = "products", key = "#id")
     */
    @Bean
    public Cache<String, Object> productsCache() {
        return Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    /**
     * Products by Category Cache - 5 minutlik TTL
     * Foydalanish: @Cacheable(value = "productsByCategory", key = "#categoryId")
     */
    @Bean
    public Cache<String, Object> productsByCategoryCache() {
        return Caffeine.newBuilder()
                .maximumSize(5_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    /**
     * User Info Cache - 30 minutlik TTL
     * Foydalanish: @Cacheable(value = "userInfo", key = "#userId")
     */
    @Bean
    public Cache<String, Object> userInfoCache() {
        return Caffeine.newBuilder()
                .maximumSize(2_000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    /**
     * Search Results Cache - 2 minutlik TTL (dinamik)
     * Foydalanish: @Cacheable(value = "searchResults", key = "#query")
     */
    @Bean
    public Cache<String, Object> searchResultsCache() {
        return Caffeine.newBuilder()
                .maximumSize(5_000)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    /**
     * Rate Limit Buckets - 1 soatlik TTL
     */
    @Bean
    public Cache<String, Object> rateLimitBucketsCache() {
        return Caffeine.newBuilder()
                .maximumSize(100_000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .recordStats()
                .build();
    }
}