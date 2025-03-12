package ac.dnd.dodal.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER("USER", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    DELETE_USER("DELETE_USER", "ROLE_DELETE_USER")
    ;

    private final String name;
    private final String securityName;
}