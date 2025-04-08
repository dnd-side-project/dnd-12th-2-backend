package ac.dnd.dodal.common.util;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.exception.InternalServerErrorException;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.ui.auth.response.KakaoUserInfoDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static ac.dnd.dodal.common.constant.Constants.*;

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

  @Value("${spring.security.oauth2.apple.client.private-key}")
  private String applePrivateKey;

  @Value("${spring.security.oauth2.apple.public-key-uri}")
  private String applePublicKeyUri;

  @Value("${spring.security.oauth2.apple.grant_type}")
  private String appleGrantType;

  @Value("${spring.security.oauth2.apple.redirect-uri}")
  private String appleRedirectUri;

  public KakaoUserInfoDto getKakaoUserInfo(String accessToken) {

    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + accessToken);
    //    httpHeaders.add(Constants.CONTENT_TYPE,
    // "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<?> kakaoProfileRequest = new HttpEntity<>(httpHeaders);
    log.info("kakaoProfileRequest" + kakaoProfileRequest);

    ResponseEntity<String> response =
        restTemplate.exchange(
            KAKAO_RESOURCE_SERVER_URL,
            HttpMethod.POST,
            kakaoProfileRequest,
            String.class);

    if (response.getBody() == null) {
      throw new RuntimeException("Kakao API 요청에 실패했습니다.");
    }

    JsonElement element = JsonParser.parseString(response.getBody());

    return KakaoUserInfoDto.of(
        element.getAsJsonObject().getAsJsonObject("kakao_account").get("email").getAsString(),
        element
            .getAsJsonObject()
            .getAsJsonObject("kakao_account")
            .getAsJsonObject("profile")
            .get("nickname")
            .getAsString(),
        element
            .getAsJsonObject()
            .getAsJsonObject("kakao_account")
            .getAsJsonObject("profile")
            .get("profile_image_url")
            .getAsString());
  }

  public String generateClientSecret() {
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
      String privateKeyPEM =
          applePrivateKey
              .replace("-----BEGIN PRIVATE KEY-----", "")
              .replace("-----END PRIVATE KEY-----%", "")
              .replaceAll("\\s+", ""); // 공백 및 줄바꿈 제거

      byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

      KeyFactory keyFactory = KeyFactory.getInstance("EC");
      return keyFactory.generatePrivate(keySpec);

    } catch (Exception e) {
      throw new RuntimeException("Error converting private key from String", e);
    }
  }

  public String getAppleAccessToken(String code) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(CONTENT_TYPE, "application/x-www-form-urlencoded");

    // 폼 데이터 설정
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("client_id", appleClientId);
    formData.add("client_secret", generateClientSecret());
    formData.add("code", code);
    formData.add("grant_type", appleGrantType);

    // 요청 엔티티 생성
    HttpEntity<MultiValueMap<String, String>> requestEntity =
        new HttpEntity<>(formData, httpHeaders);

    // POST 요청 보내기
    ResponseEntity<String> response =
        restTemplate.postForEntity(APPLE_TOKEN_URL, requestEntity, String.class);

    if (response.getBody() == null || response.getStatusCode().isError()) {
      throw new InternalServerErrorException(SecurityExceptionCode.EXTERNAL_SERVER_ERROR);
    }

    JsonElement element = JsonParser.parseString(response.getBody());

    // response.getBody에서 "access_token"을 추출하여 반환
    return element.getAsJsonObject().get("access_token").getAsString();
  }

  public <T> T decodePayload(String token, Class<T> targetClass) {
    String[] tokenParts = token.split("\\.");
    String payloadJWT = tokenParts[1];
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String payload = new String(decoder.decode(payloadJWT));
    ObjectMapper objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    log.info("Apple login response: " + payload);
    try {
      return objectMapper.readValue(payload, targetClass);
    } catch (Exception e) {
      throw new RuntimeException("Error decoding token payload", e);
    }
  }

  public void revokeAppleToken(String accessToken) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(CONTENT_TYPE, "application/x-www-form-urlencoded");

    // 폼 데이터 설정
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("client_id", appleClientId);
    formData.add("client_secret", generateClientSecret());
    formData.add("token", accessToken);

    // 요청 엔티티 생성
    HttpEntity<MultiValueMap<String, String>> requestEntity =
        new HttpEntity<>(formData, httpHeaders);

    // POST 요청 보내기
    ResponseEntity<String> response =
        restTemplate.postForEntity(
                APPLE_REVOKE_URL, requestEntity, String.class);

    if (response.getStatusCode().isError()) {
      log.info("response when user revokes to use apple token : " + response);
      throw new InternalServerErrorException(SecurityExceptionCode.EXTERNAL_SERVER_ERROR);
    }
  }
}
