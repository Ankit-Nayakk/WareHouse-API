package com.example.warehouse.security;

import com.example.warehouse.security.filters.ClientAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, ClientAuthFilter clientAuthFilter) throws Exception{
        http.csrf(csrf -> csrf.disable());

        //authorization of endpoints as public and private
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/register").permitAll()
                .requestMatchers("/client/register").permitAll()
                .anyRequest().authenticated());

        http.addFilterBefore(clientAuthFilter, UsernamePasswordAuthenticationFilter.class);

        //type of authentication to user [HttpBasic, FormLogin, AuthOLogin]
        http.formLogin(Customizer.withDefaults());

        //building
        return http.build();
    }
}
