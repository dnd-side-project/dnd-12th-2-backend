package ac.dnd.dodal.core.config.security.enums;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ESecurityCode implements ResultCode {

    TOKEN_MALFORMED_ERROR("SEC001", "Token Malformed Error"),
    TOKEN_TYPE_ERROR("SEC002", "Token Type Error"),
    EXPIRED_TOKEN_ERROR("SEC003", "Expired Token Error"),
    TOKEN_UNSUPPORTED_ERROR("SEC004", "Token Unsupported Error"),
    TOKEN_UNKNOWN_ERROR("SEC005", "Token Unknown Error"),
    INVALID_HEADER_ERROR("SEC006", "Invalid Header Error"),
    NOT_FOUND_END_POINT("SEC007", "Not Found End Point"),
    EXTERNAL_SERVER_ERROR("SEC008", "External Server Error"),
    ACCESS_DENIED_ERROR("SEC009", "Access Denied Error"),
    FAILURE_LOGOUT("SEC010", "Failure Logout"),
    FAILURE_LOGIN("SEC011", "Failure Login"),
    ;

    private final String code;
    private final String message;
}
