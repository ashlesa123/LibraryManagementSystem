package com.Library.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for Postman testing
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/addUser", "/api/auth/addBook", "/api/auth/**").permitAll() // allow unauthenticated access
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // for testing with Postman, can replace with JWT later

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
