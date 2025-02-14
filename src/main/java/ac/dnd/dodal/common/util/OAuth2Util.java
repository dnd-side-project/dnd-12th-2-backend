package ac.dnd.dodal.common.util;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.ui.auth.response.KakaoUserInfoDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class OAuth2Util {

  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${spring.security.oauth2.apple.client.client-id}")
  private String appleClientId;

  @Value("${spring.security.oauth2.apple.client.team-id}")
    private String appleTeamId;

  @Value("${spring.security.oauth2.apple.key-id}")
    private String appleKeyId;

  @Value("${spring.security.oauth2.apple.client.client-secret}")
  private String appleClientSecret;

  @Value("${spring.security.oauth2.apple.public-key-uri}")
    private String applePublicKeyUri;

  @Value("${spring.security.oauth2.apple.grant_type}")
  private String appleGrantType;

  @Value("${spring.security.oauth2.apple.redirect-uri}")
  private String appleRedirectUri;

  // 카카오 사용 시 로컬 디바이스에서 accessToken으로 사용자 정보 조회까지 모두 마친 상태에서 사용자 이름을 원하는 닉네임으로 수정하여 커스텀한 최종 사용자 정보를
  // 스프링을 넘겨주느냐
  // 아니면 accessToken만 넘겨주고 스프링에서 사용자 정보 조회까지 하는지에 대해 정해야 함.
  // 현재는 후자로 구현하지만,
  public KakaoUserInfoDto getKakaoUserInfo(String accessToken) {

    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + accessToken);
    //    httpHeaders.add(Constants.CONTENT_TYPE,
    // "application/x-www-form-urlencoded;charset=utf-8");

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
            element.getAsJsonObject().getAsJsonObject("kakao_account")
                    .get("email").getAsString(),
            element.getAsJsonObject().getAsJsonObject("kakao_account")
                    .getAsJsonObject("profile").get("nickname").getAsString(),
            element.getAsJsonObject().getAsJsonObject("kakao_account")
                    .getAsJsonObject("profile").get("profile_image_url").getAsString()
    );
  }

  private String generateClientSecret(){
    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

    return Jwts.builder()
            .setHeaderParam(JwsHeader.KEY_ID, appleKeyId)
            .setHeaderParam("alg", "ES256")
            .setIssuer(appleTeamId)
            .setAudience("https://appleid.apple.com")
            .setSubject(appleClientId)
            .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setIssuedAt(new Date())
            .signWith(readPrivateKey(), SignatureAlgorithm.ES256)
            .compact();
  }

  private PrivateKey readPrivateKey() {
    try {
      String privateKeyPEM = appleClientSecret
              .replace("-----BEGIN PRIVATE KEY-----", "")
              .replace("-----END PRIVATE KEY-----", "")
              .replaceAll("\\s+", ""); // 공백 및 줄바꿈 제거

      byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

      KeyFactory keyFactory = KeyFactory.getInstance("EC");
      return keyFactory.generatePrivate(keySpec);

    } catch (Exception e) {
      throw new RuntimeException("Error converting private key from String", e);
    }
  }

  public JsonElement verifyAuthorizationCode(String code) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(Constants.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<?> appleTokenRequest = new HttpEntity<>(httpHeaders);
    log.info("appleTokenRequest" + appleTokenRequest);

    ResponseEntity<String> response =
        restTemplate.exchange(
            Constants.APPLE_TOKEN_URL + "?client_id=" + appleClientId + "&client_secret=" + generateClientSecret() + "&code=" + code + "&grant_type=" + appleGrantType,
            HttpMethod.POST,
            appleTokenRequest,
            String.class);

    if (response.getBody() == null) {
      throw new RuntimeException("Apple API 요청에 실패했습니다.");
    }

      return JsonParser.parseString(response.getBody());
  }

  public <T> T decodePayload(String token, Class<T> targetClass) {
    String[] tokenParts = token.split("\\.");
    String payloadJWT = tokenParts[1];
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String payload = new String(decoder.decode(payloadJWT));
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    log.info("Apple 로그인 응답: " + payload);
    try {
      return objectMapper.readValue(payload, targetClass);
    } catch (Exception e) {
      throw new RuntimeException("Error decoding token payload", e);
    }
  }
}
