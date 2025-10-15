package com.example.demoproject.component;

import com.example.demoproject.entity.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public Long getUserId() {
        User user = getUser();
        return user != null ? user.getId() : null;
    }

    public String getUsername() {
        User user = getUser();
        return user != null ? user.getUsername() : "SYSTEM";
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() 
                && !(authentication.getPrincipal() instanceof String);
    }
}