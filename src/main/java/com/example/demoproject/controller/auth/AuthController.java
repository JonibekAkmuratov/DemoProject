package com.example.demoproject.controller.auth;

import com.example.demoproject.annotation.RateLimit;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.auth.*;
import com.example.demoproject.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demoproject.controller.AbstractController.PATH_DEMO;

@RestController
@RequestMapping(PATH_DEMO + "/auth")
@RequiredArgsConstructor
@RateLimit(type = RateLimit.RateLimitType.AUTH, keyPattern = "{ip}")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @RateLimit(
            type = RateLimit.RateLimitType.CUSTOM,
            capacity = 3,
            refillTokens = 3,
            refillDuration = 5,  // 5 daqiqada 3 ta
            keyPattern = "{ip}"
)
    public ResponseEntity<Data<AuthResDTO>> register(@Valid @RequestBody RegisterReqDTO request) {
        AuthResDTO response = authService.register(request);
        return new ResponseEntity<>(new Data<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @RateLimit(
            type = RateLimit.RateLimitType.CUSTOM,
            capacity = 5,
            refillTokens = 5,
            refillDuration = 1,  // 1 daqiqada 5 ta
            keyPattern = "{ip}"
    )
    public ResponseEntity<Data<AuthResDTO>> login(@Valid @RequestBody LoginReqDTO request) {
        AuthResDTO response = authService.login(request);
        return ResponseEntity.ok(new Data<>(response));
    }

    @PostMapping("/refresh")
    @RateLimit(type = RateLimit.RateLimitType.AUTH)
    public ResponseEntity<Data<AuthResDTO>> refreshToken(@Valid @RequestBody RefreshTokenReqDTO request) {
        AuthResDTO response = authService.refreshToken(request);
        return ResponseEntity.ok(new Data<>(response));
    }

    @GetMapping("/me")
    @RateLimit(type = RateLimit.RateLimitType.PER_USER, keyPattern = "{user}")
    public ResponseEntity<Data<UserInfoDTO>> getCurrentUser() {
        // TODO: Implement get current user from SecurityContext
        return ResponseEntity.ok(new Data<>(null));
    }
}