package com.pegasus.odontosys.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.DispatcherType;

@Configuration 
@EnableWebSecurity 
public class SecurityConfiguration {

    @Autowired 
    private SecurityFilter securityFilter;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http.csrf(csrf->csrf.disable())
        //.cors(cors->cors.configure(http))
        .cors(cors->cors.disable())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(autorize->autorize
        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
        .requestMatchers("/auth/login").permitAll()
        .requestMatchers("/auth/register").permitAll()
        //.requestMatchers("/auth/hello").permitAll()
        .requestMatchers("/css/**", "/js/**", "/images/**", "/fragments/**", "/favicon.ico").permitAll()
        .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
