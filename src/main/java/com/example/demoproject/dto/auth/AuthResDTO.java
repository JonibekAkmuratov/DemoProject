package com.example.demoproject.dto.auth;

import com.example.demoproject.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Auth Response
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResDTO implements DTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private UserInfoDTO user;
}
