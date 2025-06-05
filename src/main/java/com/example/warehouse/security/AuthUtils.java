package com.example.warehouse.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Objects;
import java.util.Optional;

public class AuthUtils {
    private AuthUtils() {
        //utility class
    }

    public static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    public static Optional<String> getCurrentUserName() {
        return getAuthentication().map(Authentication::getName);
    }

    public static void setAuthentication(Authentication authentication) {
        Objects.requireNonNull(authentication, "Authentication cannot be null !!!");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
