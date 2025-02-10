package ac.dnd.dodal.application.user;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    private static final Logger log = LoggerFactory.getLogger(HelloControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String accessToken;
    private String refreshToken;
    private User savedUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // 데이터 초기화

    }

    @Test
    @DisplayName("Hello API 호출 성공 - JWT에서 UserId가 정상적으로 추출되고 DB 조회됨")
    void testHelloEndpointWithUserId() throws Exception {
        // JWT 생성 (User의 ID 포함)
        refreshToken = "Bearer refreshToken";
        User user = new User("testUser", "profile.jpg", "deviceToken123", "test@example.com", UserRole.USER);
        user.updateRefreshToken(refreshToken);

        savedUser = userRepository.save(user);
        assertThat(savedUser.getRefreshToken()).isNotNull();
        accessToken = "Bearer " + jwtUtil.createToken(savedUser.getId(), savedUser.getRole(), 3600000L);


        // when: GET 요청 실행
        mockMvc.perform(get("/hello/test")
                        .header("Authorization", accessToken)  // JWT 포함
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("COM000")) // 성공 코드
                .andExpect(jsonPath("$.message").value("Success")) // 성공 메시지
                .andExpect(jsonPath("$.data").value(savedUser.getId())); // 반환된 userId가 일치하는지 확인

        // then: UserId를 통해 DB에서 사용자를 조회하고 확인
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    @DisplayName("Hello API 호출 실패 - JWT 없음")
    void testHelloEndpointWithoutJwt() throws Exception {
        mockMvc.perform(get("/hello/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()); // 401 Unauthorized
    }

    @Test
    @DisplayName("Hello API 호출 실패 - 잘못된 JWT")
    void testHelloEndpointWithInvalidJwt() throws Exception {
        mockMvc.perform(get("/hello/test")
                        .header("Authorization", "Bearer invalid-token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()); // 401 Unauthorized
    }
}