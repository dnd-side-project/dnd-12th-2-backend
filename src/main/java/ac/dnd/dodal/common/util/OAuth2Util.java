package ac.dnd.dodal.common.util;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.ui.auth.response.KakaoUserInfoDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class OAuth2Util {
  private final RestTemplate restTemplate = new RestTemplate();

  // 카카오 사용 시 로컬 디바이스에서 accessToken으로 사용자 정보 조회까지 모두 마친 상태에서 사용자 이름을 원하는 닉네임으로 수정하여 커스텀한 최종 사용자 정보를
  // 스프링을 넘겨주느냐
  // 아니면 accessToken만 넘겨주고 스프링에서 사용자 정보 조회까지 하는지에 대해 정해야 함.
  // 현재는 후자로 구현하지만,
  public KakaoUserInfoDto getKakaoUserInfo(String accessToken) {

    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + accessToken);
//    httpHeaders.add(Constants.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<?> kakaoProfileRequest = new HttpEntity<>(httpHeaders);
    log.info("kakaoProfileRequest" + kakaoProfileRequest);

    ResponseEntity<String> response =
        restTemplate.exchange(
            Constants.KAKAO_RESOURCE_SERVER_URL,
            HttpMethod.POST,
            kakaoProfileRequest,
            String.class);

    if (response.getBody() == null) {
      throw new RuntimeException("Kakao API 요청에 실패했습니다.");
    }

    JsonElement element = JsonParser.parseString(response.getBody());

    return KakaoUserInfoDto.of(
        element.getAsJsonObject().getAsJsonObject("kakao_account").get("email").getAsString());
  }
}
