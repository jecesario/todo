package com.easytech.todo.config;

import com.easytech.todo.domain.service.UserServiceImpl;
import com.easytech.todo.security.jwt.JwtAuthFilter;
import com.easytech.todo.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig {

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    // Use @Laze to fix cycle error
    public SecurityConfig(@Lazy UserServiceImpl userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userService);
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/subtask/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**")
                    .permitAll()
                .antMatchers(HttpMethod.GET, "/user/**")
                    .permitAll()
                .antMatchers(HttpMethod.POST, "/user/auth/**")
                    .permitAll()
                .anyRequest().authenticated()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
