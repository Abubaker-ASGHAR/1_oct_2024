package com.example.spring_security_basic.config;
import com.example.spring_security_basic.service.CustomerUserDetailsService;
import com.example.spring_security_basic.service.UserService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     private CustomerUserDetailsService userDetailsService;

     @Autowired
    public SecurityConfig(CustomerUserDetailsService userDetailsService)
     {
         this.userDetailsService = userDetailsService;
     }

     @Bean

     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 // Disable CSRF for simplicity; for production, consider enabling it and configuring properly
                 .csrf(csrf -> csrf.disable())

                 // Authorize requests
                 .authorizeHttpRequests(auth -> auth
                         .requestMatchers("/register", "/login", "/css/**").permitAll()
                         .anyRequest().authenticated())

                 // Form login configuration
                 .formLogin(form -> form
                         .loginPage("/login")
                         .defaultSuccessUrl("/", true)
                         .permitAll())

                 // Logout configuration
                 .logout(logout -> logout
                         .logoutSuccessUrl("/login?logout")
                         .permitAll())

                 // Session management
                 .sessionManagement(session -> session
                         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

         return http.build();
     }

     @Bean
    public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }

     @Bean
    public  DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
