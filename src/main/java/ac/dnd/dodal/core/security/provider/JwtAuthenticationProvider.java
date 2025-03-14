package ac.dnd.dodal.core.security.provider;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.core.security.JwtAuthenticationToken;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.core.security.info.CustomUserDetails;
import ac.dnd.dodal.core.security.info.JwtUserInfo;
import ac.dnd.dodal.core.security.service.CustomUserDetailsService;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication){
        if (authentication instanceof JwtAuthenticationToken) {
            JwtUserInfo jwtUserInfo = JwtUserInfo.builder()
                    .id((Long) authentication.getPrincipal())
                    .role((UserRole) authentication.getCredentials())
                    .build();

            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUserId(jwtUserInfo.id());

            if (userDetails.getRole() != jwtUserInfo.role()) {
                throw new UserBadRequestException(UserExceptionCode.NO_SUCH_ROLE) {
                };
            }

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        throw new BadRequestException(SecurityExceptionCode.INVALID_AUTHENTICATION);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}