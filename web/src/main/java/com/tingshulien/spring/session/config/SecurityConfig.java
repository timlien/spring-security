package com.tingshulien.spring.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/signup", "/register", "/signin", "/login").anonymous()
                        .requestMatchers("/", "/home", "/logout", "/password").authenticated()
                        .requestMatchers("/error/**", "/favicon.ico", "/js/**", "/css/**").permitAll())
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
                .formLogin(customizer -> customizer
                        .loginPage("/signin")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home?success=true")
                        .failureUrl("/signin?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password"))
                .logout(customizer -> customizer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/signin"))
                .exceptionHandling(customizer -> customizer
                        .accessDeniedPage("/error/403"))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
