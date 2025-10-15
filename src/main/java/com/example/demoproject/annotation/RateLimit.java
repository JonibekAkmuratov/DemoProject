package com.example.demoproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Rate limiting annotation for API endpoints
 * Usage: @RateLimit(type = RateLimitType.API)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    
    /**
     * Rate limit type
     */
    RateLimitType type() default RateLimitType.GLOBAL;
    
    /**
     * Custom capacity (optional, overrides config)
     */
    int capacity() default -1;
    
    /**
     * Custom refill tokens (optional, overrides config)
     */
    int refillTokens() default -1;
    
    /**
     * Custom refill duration in minutes (optional, overrides config)
     */
    int refillDuration() default -1;
    
    /**
     * Rate limit key pattern
     * Supports: {ip}, {user}, {path}, {method}
     */
    String keyPattern() default "{ip}";
    
    enum RateLimitType {
        GLOBAL,    // Barcha foydalanuvchilar uchun
        PER_USER,  // Har bir foydalanuvchi uchun
        PER_IP,    // Har bir IP uchun
        AUTH,      // Authentication endpointlar uchun
        API,       // API endpointlar uchun
        CUSTOM     // Custom configuration
    }
}