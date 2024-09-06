package br.thullyoo.twitter_clone_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST,"/register")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
