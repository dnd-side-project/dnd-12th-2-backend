package ac.dnd.dodal;

import jakarta.transaction.Transactional;

import org.springframework.test.context.ActiveProfiles;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTest {

    // integration test에서 사용할 것들 모아놓기
}
