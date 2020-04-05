package com.langworthytech.simplebillingsystem.config;

import com.langworthytech.simplebillingsystem.security.CustomUserDetailsService;
import com.langworthytech.simplebillingsystem.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
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
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("Running AuthenticationManagerBuilder (User Details Service)");

//        authenticationManagerBuilder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//        .usersByUsernameQuery("SELECT email as username, password, enabled from users where email = ?")
//        .authoritiesByUsernameQuery(
//                "SELECT u.email as username, a.authority " +
//                "FROM authority a, users u " +
//                "where u.email = ? " +
//                "AND u.id = a.custom_user_id");

        authenticationManagerBuilder.userDetailsService(
                customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        // This is commented because if a customer Authentication Provider is used, then the User Details Service is not called
        //authenticationManagerBuilder.authenticationProvider(new BasicHttpAuthenticationProvider(userRepository));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                .username("tony")
//                .password("pass")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
