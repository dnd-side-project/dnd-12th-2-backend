package ac.dnd.dodal;

import jakarta.transaction.Transactional;

import org.jasypt.encryption.StringEncryptor;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.application.goal.service.GoalStatisticsService;
import ac.dnd.dodal.application.goal.service.GoalService;
import ac.dnd.dodal.application.goal.service.GoalStatisticsEventListener;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.goal.repository.GoalRepository;
import ac.dnd.dodal.application.goal.repository.GoalStatisticsRepository;
import ac.dnd.dodal.application.plan.repository.PlanRepository;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ApplicationEventPublisher eventPublisher;
    @Autowired
    protected GoalStatisticsEventListener goalStatisticsEventListener;
    @Autowired
    protected GoalStatisticsService goalStatisticsService;
    @Autowired
    protected GoalService goalService;
    @Autowired
    protected GoalRepository goalRepository;
    @Autowired
    protected GoalStatisticsRepository goalStatisticsRepository;
    @Autowired
    protected PlanRepository planRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected JwtUtil jwtUtil;

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    protected StringEncryptor stringEncryptor;
}
