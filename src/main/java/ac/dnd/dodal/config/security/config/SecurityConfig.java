package ac.dnd.dodal.config.security.config;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.config.security.filter.CustomLogoutFilter;
import ac.dnd.dodal.config.security.filter.JwtAuthenticationFilter;
import ac.dnd.dodal.config.security.filter.JwtExceptionFilter;
import ac.dnd.dodal.config.security.handler.CustomSignOutProcessHandler;
import ac.dnd.dodal.config.security.handler.CustomSignOutResultHandler;
import ac.dnd.dodal.config.security.provider.JwtAuthenticationProvider;
import ac.dnd.dodal.config.security.service.CustomUserDetailsService;
import ac.dnd.dodal.config.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomSignOutProcessHandler customSignOutProcessHandler;
    private final CustomSignOutResultHandler customSignOutResultHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(Constants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())

                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/auth/sign-out")
                                .addLogoutHandler(customSignOutProcessHandler)
                                .logoutSuccessHandler(customSignOutResultHandler)
                                .deleteCookies(Constants.AUTHORIZATION_HEADER, Constants.REAUTHORIZATION))

                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil,
                                new JwtAuthenticationProvider(customUserDetailsService)),
                        LogoutFilter.class)
                .addFilterAfter(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .addFilterBefore(new CustomLogoutFilter(), LogoutFilter.class)
                .build();
    }
}
