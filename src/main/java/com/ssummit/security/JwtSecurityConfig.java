package com.ssummit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Lazy
@EnableGlobalMethodSecurity(securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class JwtSecurityConfig
        implements WebMvcConfigurer {

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/css/**",
            "/img/**",
            "/js/**",
            "/api/authorization",
            "/api/registration",
            "/user/restore-password"
    };

    private final JwtTokenFilter jwtTokenFilter;

    public JwtSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain filterChainJwt(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                //включаем базовую авторизацию
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //Доступ для всех пользователей
                .antMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, ex) -> response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage()
                ))
                .and().authorizeRequests()
                //Доступ только для авторизованных пользователей
                .antMatchers("/tour/tour-description/{tourId}",
                        "/user/tour-revoke",
                        "/user/scheduled-tours",
                        "/tour/tour-register",
                        "/user/restore-password",
                        "/user//change-password",
                        "/user/registration",
                        "/user/create-spectator",
                        "/update/{id}",
                        "/get/{id}").hasRole("PARTICIPANT")
                .antMatchers("/tour/set-route",
                        "/tour/tour-equipment/{tourId}",
                        "/tour/tour-get-guides-and-participants/{tourId}")
                .hasRole("GUIDE")
                .antMatchers("/tour/tour-checkpoints-marks/{tourId}",
                        "/tour/tour-last-checkpoint/{tourId}",
                        "/user/restore-password")
                .hasRole("SPECTATOR")
                .antMatchers("/user/**",
                        "/tour/tour-get-guides-and-participants/{tourId}",
                        "/tour/tour-last-checkpoint/{tourId}",
                        "/tour/tour-checkpoints-marks/{tourId}",
                        "/tour/schedule-a-tour",
                        "/tour/set-primary-guide",
                        "/tour/set-secondary-guide",
                        "/tour/set-route",
                        "/tour/tour-set-checkpoint-scheduled-marked-time",
                        "/tour/tour-get-scheduled-tours",
                        "/tour/tour-get-required-equipment/{tourId}",
                        "/tour/tour-fill-tour-application{tourId}",
                        "/tour/tour-final-check/{tourId}",
                        "/tour/tour-description/{tourId}",
                        "/tour-equipment/**",
                        "/tour-application/**",
                        "/route/**",
                        "/role/**",
                        "/message-type/**",
                        "/message/**",
                        "/checkpoint-mark/**",
                        "/item-type/**",
                        "/item/**",
                        "/checkpoint/**"
                )
                .hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}

