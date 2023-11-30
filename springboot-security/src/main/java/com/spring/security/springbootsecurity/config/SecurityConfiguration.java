package com.spring.security.springbootsecurity.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final jwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

//        http.headers().frameOptions().disable();
                http.csrf().disable()

//                Implementing white listening means no need of add security to some of urls
                .authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/api/V1/auth/**")).permitAll()
//                        .authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                Here i will apply security to other requests
                .anyRequest().authenticated()
//                Set authentication to each request its mens we dont need to store sessions
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                Now Set authentication provider here
                .and().authenticationProvider(authenticationProvider)
//                Now set a jwt filter here
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();


//        http.
////                Disable CSRF
//                csrf() .disable()
////                Implementing white listening means no need of add security to some of urls
//                .authorizeHttpRequests().requestMatchers("/api/V1/auth/**").permitAll()
////                Here i will apply security to other requests
//                .anyRequest().authenticated()
////                Set authentication to each request its mens we dont need to store sessions
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                Now Set authentication provider here
//                .and().authenticationProvider(authenticationProvider)
////                Now set a jwt filter here
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//        This is working
//        http.csrf().disable();
//        http.authorizeHttpRequests((autherize)-> {
//            try {
//                autherize.
//                        requestMatchers(new AntPathRequestMatcher("/api/V1/auth/**")).permitAll().
////                        requestMatchers(new AntPathRequestMatcher("/h2-console/**") ).permitAll().
//                        anyRequest().authenticated()
//                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .and().authenticationProvider(authenticationProvider)
//                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//        http.headers().frameOptions().disable();
//
//
//
//
//        return http.build();
    }
}
