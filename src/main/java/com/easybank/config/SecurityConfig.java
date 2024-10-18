package com.easybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/contact", "/notices", "/error").permitAll()
                .requestMatchers("/myLoans", "/myAccount", "/myBalance", "/myCards").authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    // // For in-memory authentication
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
    //     UserDetails admin = User.withUsername("sandeep")
    //             .password("{bcrypt}$2a$12$paveyXY/xOWEsAhTUg901.mhdo.DtWYdPwI6bEv31fyyMQJL9hLOu")
    //             .authorities("admin").build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    // // For encoding the password, uses bcrypt by default
    // // can be changed to other encoders by using different prefixes
    // // {bcrypt}, {noop}, {pbkdf2}, {scrypt}, {sha256}
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    // }

    // // For checking by calling an external API to check if the password is
    // // compromised
    // @Bean
    // public CompromisedPasswordChecker compromisedPasswordChecker() {
    //     return new HaveIBeenPwnedRestApiPasswordChecker();
    // }

}