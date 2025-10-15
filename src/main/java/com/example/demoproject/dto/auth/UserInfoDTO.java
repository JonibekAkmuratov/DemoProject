package com.example.demoproject.dto.auth;

import com.example.demoproject.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// User Info
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements DTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String role;
}
