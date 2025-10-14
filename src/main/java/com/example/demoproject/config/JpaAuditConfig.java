package com.example.demoproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;



@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaAuditConfig {

//    private final CurrentUser currentUser;
//
//    @Bean
//    public AuditorAware<Object> auditorAware() {
//        return () -> Optional.of(currentUser.userID());
//    }

}
