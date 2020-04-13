package com.langworthytech.simplebillingsystem.config;

import com.langworthytech.simplebillingsystem.security.CustomUserDetailsService;
import com.langworthytech.simplebillingsystem.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, DataSource dataSource) {
//        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/static/**", "/webjars/**", "/css/**").permitAll()
                    .antMatchers("/", "/register").permitAll()
//                    .antMatchers("/api/v1/**").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/home").hasAuthority("READ_PRIVILEGE")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .permitAll()
                    .and()
                    .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("Running AuthenticationManagerBuilder (User Details Service)");

        authenticationManagerBuilder.userDetailsService(
                customUserDetailsService)
                .passwordEncoder(passwordEncoder());

        // for testing only
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("hello")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("hello")).roles("USER")
//                .and()
//                .withUser("manager").password(passwordEncoder().encode("hello")).roles("MANAGER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
