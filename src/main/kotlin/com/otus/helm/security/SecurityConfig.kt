package com.otus.helm.security

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .authorizeExchange()
            .anyExchange().permitAll()
            .and()
            .build()
    }

//    @Bean
//    fun userDetailsService(): MapReactiveUserDetailsService? {
//        val user = User
//            .withUsername("user")
//            .password(passwordEncoder().encode("password"))
//            .roles("USER")
//            .build()
//        val admin = User
//            .withUsername("admin")
//            .password(passwordEncoder().encode("password"))
//            .roles("ADMIN")
//            .build()
//        return MapReactiveUserDetailsService(UserPrincipal(), admin)
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}