package com.example.demoproject.criteria.auth;

import com.example.demoproject.criteria.GenericCriteria;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserCriteria extends GenericCriteria {

    private String username;
    private String email;
    private String role;
    private Boolean enabled;
    private Boolean deleted;

}
