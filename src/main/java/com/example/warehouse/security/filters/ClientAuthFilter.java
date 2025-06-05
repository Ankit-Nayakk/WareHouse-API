package com.example.warehouse.security.filters;

import com.example.warehouse.entity.Client;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.exceptions.ClientNotFoundWithApiKeyException;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.security.AuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class ClientAuthFilter extends OncePerRequestFilter {

    private static final String X_API_KEY = "X-Api-Key";
    private static final String X_SECRET_KEY = "X-Secret-Key";

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(X_API_KEY);
        String secretKey = request.getHeader(X_SECRET_KEY);

        if (isValid(apiKey) && isValid(secretKey)) {
            var client = clientRepository.findByApiKey(apiKey)
                    .orElseThrow(() -> new ClientNotFoundWithApiKeyException("Client Mot Found !!!"));
            var doesMatch = passwordEncoder.matches(secretKey, client.getSecretKey());

            if(doesMatch && AuthUtils.getAuthentication().isEmpty()) {
                var token = new UsernamePasswordAuthenticationToken(client.getOrganizationName(), null,
                                    List.of(new SimpleGrantedAuthority(UserRole.CLIENT.name())));

                AuthUtils.setAuthentication(token);

            }
        }

        filterChain.doFilter(request, response);
    }

    private static boolean isValid(String s) {
        return s != null && !s.isBlank();
    }
}
