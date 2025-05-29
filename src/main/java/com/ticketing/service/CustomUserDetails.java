package com.ticketing.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ticketing.domain.Role;
import com.ticketing.domain.User;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
        		.map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
//        		.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
        		.peek(r -> System.out.println("After filter: " + r))
        		.peek(r -> System.out.println("Authority assigned: " + r.getAuthority()))
                .collect(Collectors.toList());
    }



    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
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

