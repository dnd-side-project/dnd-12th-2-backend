package ac.dnd.dodal.ui.auth.response;

public record AppleIdTokenParsingDto(
        String iss,
        String aud,
        String exp,
        String iat,
        String sub,
        String at_hash,
        String email,
        boolean email_verified,
        String auth_time,
        boolean nonce_supported
) {}
