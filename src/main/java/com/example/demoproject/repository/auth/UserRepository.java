package com.example.demoproject.repository.auth;

import com.example.demoproject.criteria.auth.UserCriteria;
import com.example.demoproject.entity.auth.User;
import com.example.demoproject.repository.GenericCrudRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    boolean existsByUsername( String username);

    boolean existsByEmail( String email);

}
