package ac.dnd.dodal.core.security.config;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.security.JwtAuthEntryPoint;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.core.security.filter.CustomLogoutFilter;
import ac.dnd.dodal.core.security.filter.JwtAuthenticationFilter;
import ac.dnd.dodal.core.security.filter.JwtExceptionFilter;
import ac.dnd.dodal.core.security.handler.CustomSignOutProcessHandler;
import ac.dnd.dodal.core.security.handler.CustomSignOutResultHandler;
import ac.dnd.dodal.core.security.handler.JwtAccessDeniedHandler;
import ac.dnd.dodal.core.security.provider.JwtAuthenticationProvider;
import ac.dnd.dodal.core.security.service.CustomUserDetailsService;
import ac.dnd.dodal.core.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomSignOutProcessHandler customSignOutProcessHandler;
    private final CustomSignOutResultHandler customSignOutResultHandler;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(Constants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())

                .formLogin(AbstractHttpConfigurer::disable)

                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/auth/sign-out")
                                .addLogoutHandler(customSignOutProcessHandler)
                                .logoutSuccessHandler(customSignOutResultHandler)
                                .deleteCookies(Constants.AUTHORIZATION_HEADER, Constants.REAUTHORIZATION))

                .addFilterBefore(new CustomLogoutFilter(), LogoutFilter.class)
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil,
                                new JwtAuthenticationProvider(customUserDetailsService)),
                        CustomLogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .build();
    }
}