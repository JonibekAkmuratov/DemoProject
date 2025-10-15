package com.example.demoproject.config.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rate-limit")
public class RateLimitConfig {

    private boolean enabled = true;
    private LimitConfig global = new LimitConfig();
    private LimitConfig auth = new LimitConfig();
    private LimitConfig api = new LimitConfig();
    private LimitConfig perUser = new LimitConfig();

    @Data
    public static class LimitConfig {
        private int capacity = 100;
        private int refillTokens = 100;
        private int refillDuration = 1; // minutes

        public Bandwidth toBandwidth() {
            return Bandwidth.classic(
                capacity,
                Refill.intervally(refillTokens, Duration.ofMinutes(refillDuration))
            );
        }
    }
}