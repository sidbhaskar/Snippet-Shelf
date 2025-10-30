package com.ss.snippetservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Apply CORS settings from your WebConfig
                .cors(withDefaults())

                // 2. Disable CSRF for stateless APIs (common for frontends like React)
                .csrf(AbstractHttpConfigurer::disable)

                // 3. Define authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests to any endpoint for now.
                        // You can restrict this later (e.g., .requestMatchers("/api/admin/**").authenticated())
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}