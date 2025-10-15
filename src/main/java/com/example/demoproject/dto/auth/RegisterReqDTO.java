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

// Register Request
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDTO implements DTO {

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = ErrorCode.INPUT_CANNOT_BE_BLANK)
    private String fullName;
}
