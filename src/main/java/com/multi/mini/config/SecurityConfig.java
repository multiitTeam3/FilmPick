package com.multi.mini.config;

import com.multi.mini.common.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CustomAuthenticationProvider 원본 비밀번호와 임시 비밀번호 동시 사용을 위해 커스텀
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService, bCryptPasswordEncoder());
    }

    @Bean
    public WebSecurityCustomizer configure() {

        return (web) -> web.ignoring().requestMatchers(
                "/css/**", "/js/**", "/img/**"
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/community/list", "/community/view").permitAll()
                        .requestMatchers("/Customer/Index", "/Customer/NoticeDetail").permitAll()

                        .requestMatchers("/", "/home", "/login", "/sign-up", "/error", "/clearMessage", "/password", "/movie/findMovieList", "/member/isEmail", "/member/isName", "/gpt/chatBot").permitAll()
                        .anyRequest().authenticated()
                        );
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/login").permitAll());

        http
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/"));

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .exceptionHandling(exceptionHandling ->
                                exceptionHandling
                                        .accessDeniedPage("/error/denied"));

        http
                .csrf((auth) -> auth.disable());


        return http.build();
    }

}
//"/admin" ,