package com.example;

import com.example.controller.DataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${spring.h2.console.path}")
    private String h2Path;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").authorities("ROLE_USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web
            .ignoring()
                .antMatchers(h2Path + "/**");
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable();

        http
         .authorizeRequests()
            .antMatchers("/cache/**").permitAll()
            .antMatchers(h2Path + "/**").permitAll()
            .antMatchers(DataController.THREAD + "/**").permitAll()
            .antMatchers("/students/**").permitAll()
            .antMatchers("/securityNone").permitAll()
          .anyRequest().authenticated().and().httpBasic();
        // @formatter:on

    }
}