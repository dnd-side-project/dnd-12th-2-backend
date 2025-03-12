package ac.dnd.dodal.core.security.filter;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.core.security.JwtAuthenticationToken;
import ac.dnd.dodal.core.security.info.JwtUserInfo;
import ac.dnd.dodal.core.security.provider.JwtAuthenticationProvider;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;

import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return Constants.NO_NEED_AUTH_URLS.stream().anyMatch(pattern -> antPathMatcher.match(pattern, requestURI));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String token = jwtUtil.getJwtFromRequest(request);

            if (StringUtils.hasText(token)) {
                Claims claims = jwtUtil.validateAndGetClaimsFromToken(token);
                JwtUserInfo jwtUserInfo = JwtUserInfo.builder()
                        .id(claims.get(Constants.USER_ID_CLAIM_NAME, Long.class))
                        .role(UserRole
                                .valueOf(claims.get(Constants.USER_ROLE_CLAIM_NAME, String.class)))
                        .build();

                JwtAuthenticationToken beforeAuthentication =
                        new JwtAuthenticationToken(null, jwtUserInfo.id(), jwtUserInfo.role());

                UsernamePasswordAuthenticationToken afterAuthentication =
                        (UsernamePasswordAuthenticationToken) jwtAuthenticationProvider
                                .authenticate(beforeAuthentication);
                afterAuthentication
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(afterAuthentication);
                SecurityContextHolder.setContext(securityContext);
            }

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            filterChain.doFilter(request, response);
        }
    }
}