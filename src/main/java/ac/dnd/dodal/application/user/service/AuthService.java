package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.common.constant.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public class AuthService {

  public static String refineToken(String accessToken) {
    return accessToken.startsWith(Constants.BEARER_PREFIX)
        ? accessToken.substring(Constants.BEARER_PREFIX.length())
        : accessToken;
  }
}
