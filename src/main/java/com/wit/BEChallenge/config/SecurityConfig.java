//2d573593-a72c-4d77-bd2f-5fc3bc7a07c6
package com.wit.BEChallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService){ //authManager veritabanı üzerinden authantication yapacağımızı söyler
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
        //corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().configurationSource(corsConfigurationSource());//CORS hatasına karşılık
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/user/**").permitAll();
                    auth.requestMatchers("/roles/**").permitAll();
                    auth.requestMatchers("/categories/**").permitAll();
                    auth.requestMatchers("/products/**").permitAll();
                    auth.requestMatchers("/admin/**").hasAuthority("admin"); //buraya gidecek kullanıcı admin olmalı
                    //auth.requestMatchers("/welcome/**").hasAnyAuthority("USER", "ADMIN");
                    //auth.requestMatchers("/user/login**").hasAnyAuthority("user", "admin"); bunu kapatında 403 sorununu çözdüm
                    auth.anyRequest().authenticated(); //üsttekiler hariç tüm requestler authanticated olmalı demek, bu UserService'de loadByUserName çağırıyor
                })
                .formLogin(Customizer.withDefaults()) //bunu kapatınca chrome'dan form çıkmaz popup çıkar
                .httpBasic(Customizer.withDefaults()) //postman gibi
                //.oauth2Login(Customizer.withDefaults())
                .build();

        //401 UNAUTHORIZED login olmadığın için aldığın için
        //403 FORBIDDEN login olma hakkın var ama yetkin yok

        //hasAnyAuthority kullanıcının bu yetkilerden herhangi birine sahip olup olmadığını kontrol eder
        //hasAuthority kullanıcının bu yetkiye sahip olup olmadığını kontrol eder

        //authorizatiın yetki-yetkilendirme
        //authantication kimlik doğrulama-login olma
    }
}
