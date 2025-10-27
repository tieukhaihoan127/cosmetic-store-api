package com.vincent.beauty_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}password").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}admin123").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/categories/**").permitAll()
                .requestMatchers("/api/v1/admin/categories/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/brands/**").permitAll()
                .requestMatchers("/api/v1/admin/brands/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/answers/**").permitAll()
                .requestMatchers("/api/v1/admin/answers/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/clients/**").permitAll()
                .requestMatchers("/api/v1/admin/clients/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/products/**").permitAll()
                .requestMatchers("/api/v1/admin/products/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/questions/**").permitAll()
                .requestMatchers("/api/v1/admin/questions/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/ratings/**").permitAll()
                .requestMatchers("/api/v1/admin/ratings/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/stores/**").permitAll()
                .requestMatchers("/api/v1/admin/stores/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/admin/permissions/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/admin/roles/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
