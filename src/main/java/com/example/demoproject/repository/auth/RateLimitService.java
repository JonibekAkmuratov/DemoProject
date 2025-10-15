package com.example.demoproject.repository.auth;

import com.example.demoproject.annotation.RateLimit;
import com.example.demoproject.config.security.RateLimitConfig;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final RateLimitConfig rateLimitConfig;
    private final HttpServletRequest request;

    // Cache to store buckets
    private final Cache<String, Bucket> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    /**
     * Resolve rate limit key based on pattern
     */
    public String resolveKey(RateLimit rateLimit, String fallbackKey) {
        String pattern = rateLimit.keyPattern();
        
        String key = pattern
                .replace("{ip}", getClientIp())
                .replace("{user}", getCurrentUsername())
                .replace("{path}", request.getRequestURI())
                .replace("{method}", request.getMethod());

        return key.isEmpty() ? fallbackKey : key;
    }

    /**
     * Try to consume token from bucket
     */
    public RateLimitResult tryConsume(String key, RateLimit rateLimit) {
        if (!rateLimitConfig.isEnabled()) {
            return RateLimitResult.ok();
        }

        Bucket bucket = resolveBucket(key, rateLimit);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            return RateLimitResult.ok(probe.getRemainingTokens());
        } else {
            long waitForRefill = TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill());
            log.warn("Rate limit exceeded for key: {}, wait: {} seconds", key, waitForRefill);
            return RateLimitResult.rejected(waitForRefill);
        }
    }

    /**
     * Resolve bucket based on rate limit configuration
     */
    private Bucket resolveBucket(String key, RateLimit rateLimit) {
        return cache.get(key, k -> createBucket(rateLimit));
    }

    /**
     * Create new bucket with bandwidth
     */
    private Bucket createBucket(RateLimit rateLimit) {
        Bandwidth bandwidth = getBandwidth(rateLimit);
        return Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }

    /**
     * Get bandwidth configuration
     */
    private Bandwidth getBandwidth(RateLimit rateLimit) {
        // Custom configuration
        if (rateLimit.capacity() > 0) {
            int capacity = rateLimit.capacity();
            int refillTokens = rateLimit.refillTokens() > 0 
                ? rateLimit.refillTokens() 
                : capacity;
            int refillDuration = rateLimit.refillDuration() > 0 
                ? rateLimit.refillDuration() 
                : 1;

            return Bandwidth.classic(
                capacity,
                Refill.intervally(refillTokens, Duration.ofMinutes(refillDuration))
            );
        }

        // Predefined configuration
        return switch (rateLimit.type()) {
            case AUTH -> rateLimitConfig.getAuth().toBandwidth();
            case API -> rateLimitConfig.getApi().toBandwidth();
            case PER_USER -> rateLimitConfig.getPerUser().toBandwidth();
            case PER_IP -> rateLimitConfig.getGlobal().toBandwidth();
            case CUSTOM -> rateLimitConfig.getGlobal().toBandwidth();
            default -> rateLimitConfig.getGlobal().toBandwidth();
        };
    }

    /**
     * Get client IP address
     */
    private String getClientIp() {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }

        return request.getRemoteAddr();
    }

    /**
     * Get current username from security context
     */
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonymous";
    }

    /**
     * Rate limit result
     */
    public record RateLimitResult(
            boolean allowed,
            long remainingTokens,
            long waitSeconds
    ) {
        public static RateLimitResult ok() {
            return new RateLimitResult(true, -1, 0);
        }

        public static RateLimitResult ok(long remainingTokens) {
            return new RateLimitResult(true, remainingTokens, 0);
        }

        public static RateLimitResult rejected(long waitSeconds) {
            return new RateLimitResult(false, 0, waitSeconds);
        }
    }
}