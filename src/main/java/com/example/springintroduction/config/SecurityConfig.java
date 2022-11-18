package com.example.springintroduction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    
    // 빈 등록
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }
}
