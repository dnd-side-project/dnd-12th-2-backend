package ac.dnd.dodal.core.config.security.provider;

import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.core.config.security.JwtAuthenticationToken;
import ac.dnd.dodal.core.config.security.enums.ESecurityCode;
import ac.dnd.dodal.core.config.security.info.CustomUserDetails;
import ac.dnd.dodal.core.config.security.info.JwtUserInfo;
import ac.dnd.dodal.core.config.security.service.CustomUserDetailsService;
import ac.dnd.dodal.domain.user.enums.EUserCode;
import ac.dnd.dodal.domain.user.enums.EUserRole;
import ac.dnd.dodal.domain.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
                    .role((EUserRole) authentication.getCredentials())
                    .build();

            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUserId(jwtUserInfo.id());

            if (userDetails.getRole() != jwtUserInfo.role()) {
                throw new UserException(EUserCode.NO_SUCH_ROLE) {
                };
            }

            return new JwtAuthenticationToken(userDetails.getAuthorities(), jwtUserInfo.id(), jwtUserInfo.role());
        }

        throw new BadRequestException(ESecurityCode.INVALID_AUTHENTICATION);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}