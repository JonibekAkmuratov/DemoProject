package com.example.demoproject.dto.auth;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Refresh Token Request
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenReqDTO implements DTO {

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    private String refreshToken;

}