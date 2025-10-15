package com.example.demoproject.dto.auth;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Login Request
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDTO implements DTO {
    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    private String username;

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    private String password;
}
