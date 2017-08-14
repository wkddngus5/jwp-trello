package com.example.demo.web.config;

import com.example.demo.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    abstract void configureCsrf(HttpSecurity http) throws Exception;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureCsrf(http);

//        http.headers().frameOptions().disable();

        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Configuration
    @EnableWebSecurity
    @EnableOAuth2Sso
    @Profile({"local", "dev", "prod"})
    @Slf4j
    static class NotTestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureCsrf(HttpSecurity http) throws Exception {
            log.info("enable csrf test profile");
//            http.csrf().disable();
        }
    }

    @Configuration
    @EnableWebSecurity
    @Profile({"test"})
    @Slf4j
    static class TestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureCsrf(HttpSecurity http) throws Exception {
            log.info("disable csrf test profile");
            http.csrf().disable();
        }
    }
}
