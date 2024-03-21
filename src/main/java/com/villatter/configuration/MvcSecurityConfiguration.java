package com.villatter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf)-> csrf
                        .disable()
                ).authorizeHttpRequests((request) -> request
                        .requestMatchers("/","/resources/**", "/account/**", "/check/**", "/formulir/**").permitAll()
                        .requestMatchers("/penduduk/**", "/pengajuan/**", "/selesai/**").hasAuthority("administrator")
                        .anyRequest().authenticated()
                ).formLogin((form) -> form
                        .loginPage("/account/loginForm")
                        .loginProcessingUrl("/authenticate")
                        .failureUrl("/account/failedLogin")
                        .defaultSuccessUrl("/dashboard", true)
                ).rememberMe((context) -> context
                        .key("generate-key-cookie-context")
                        .rememberMeCookieName("remember-me")
                        .tokenValiditySeconds(86400)
                ).logout((logout) -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID", "remember-me").permitAll()
                ).exceptionHandling((exception) -> exception
                        .accessDeniedPage("/account/accessDenied")
                );
        return httpSecurity.build();
    }
}