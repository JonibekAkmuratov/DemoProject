package com.example.demoproject.controller.auth;

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
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Data<AuthResDTO>> register(@Valid @RequestBody RegisterReqDTO request) {
        AuthResDTO response = authService.register(request);
        return new ResponseEntity<>(new Data<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Data<AuthResDTO>> login(@Valid @RequestBody LoginReqDTO request) {
        AuthResDTO response = authService.login(request);
        return ResponseEntity.ok(new Data<>(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Data<AuthResDTO>> refreshToken(@Valid @RequestBody RefreshTokenReqDTO request) {
        AuthResDTO response = authService.refreshToken(request);
        return ResponseEntity.ok(new Data<>(response));
    }

    @GetMapping("/me")
    public ResponseEntity<Data<UserInfoDTO>> getCurrentUser() {
        // TODO: Implement get current user from SecurityContext
        return ResponseEntity.ok(new Data<>(null));
    }
}