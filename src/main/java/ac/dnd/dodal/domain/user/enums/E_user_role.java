package ac.dnd.dodal.domain.user.enums;

import ac.dnd.dodal.domain.user.exception.UserException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum E_user_role {
    USER("USER", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    GUEST("GUEST", "ROLE_GUEST");

    private final String name;
    private final String securityName;

}