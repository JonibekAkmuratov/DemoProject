package com.example.demoproject.dto.main.auth;

import com.example.demoproject.dto.DTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.cert.CertificateEncodingException;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
public final class CurrentUserDetailsDTO implements UserDetails, DTO {
    private String serialNumber;
    private List<? extends GrantedAuthority> authorities;


    public CurrentUserDetailsDTO(String serialNumber, List<? extends GrantedAuthority> authorities) throws CertificateEncodingException {
        this.serialNumber = serialNumber;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return serialNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
