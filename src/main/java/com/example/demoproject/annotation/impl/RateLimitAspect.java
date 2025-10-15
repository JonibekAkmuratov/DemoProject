package com.example.demoproject.annotation.impl;

import com.example.demoproject.annotation.RateLimit;
import com.example.demoproject.exceptions.RateLimitExceededException;
import com.example.demoproject.repository.auth.RateLimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final RateLimitService rateLimitService;

    @Around("@annotation(com.example.demoproject.annotation.RateLimit) || " +
            "@within(com.example.demoproject.annotation.RateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RateLimit rateLimit = signature.getMethod().getAnnotation(RateLimit.class);

        // If annotation not on method, check class level
        if (rateLimit == null) {
            rateLimit = joinPoint.getTarget().getClass().getAnnotation(RateLimit.class);
        }

        if (rateLimit == null) {
            return joinPoint.proceed();
        }

        // Generate key
        String key = rateLimitService.resolveKey(
            rateLimit,
            joinPoint.getSignature().toShortString()
        );

        log.debug("Rate limit check for key: {}", key);

        // Try to consume token
        RateLimitService.RateLimitResult result = rateLimitService.tryConsume(key, rateLimit);

        if (!result.allowed()) {
            throw new RateLimitExceededException(result.waitSeconds());
        }

        log.debug("Rate limit check passed. Remaining tokens: {}", result.remainingTokens());
        return joinPoint.proceed();
    }
}