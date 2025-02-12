package ac.dnd.dodal.application.plan.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.application.plan.repository.PlanRepository;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanService planService;

    private Plan plan;
    private Plan beforeSavePlan;
    private Long planId;
    private Long historyId;

    @BeforeEach
    public void setUp() {
        plan = PlanFixture.plan();
        beforeSavePlan = new Plan(plan.getTitle(), plan.getStartDate(), plan.getEndDate());
        planId = plan.getPlanId();

        lenient().when(planRepository.save(beforeSavePlan)).thenReturn(plan);
        lenient().when(planRepository.saveAndFlush(beforeSavePlan)).thenAnswer(invocation -> {
            Plan savedPlan = invocation.getArgument(0);
            ReflectionTestUtils.setField(savedPlan, "planId", planId);
            ReflectionTestUtils.setField(savedPlan, "createdAt", plan.getCreatedAt());
            ReflectionTestUtils.setField(savedPlan, "updatedAt", plan.getUpdatedAt());
            return savedPlan;
        });
        lenient().when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        lenient().when(planRepository.findLatestPlanByHistoryId(historyId))
                .thenReturn(Optional.of(plan));
    }

    @Test
    @DisplayName("save plan test")
    public void save_plan_test() {
        // when
        Plan savedPlan = planService.save(beforeSavePlan);

        // then
        assertThat(savedPlan.getTitle()).isEqualTo(beforeSavePlan.getTitle());
        assertThat(savedPlan.getStartDate()).isEqualTo(beforeSavePlan.getStartDate());
        assertThat(savedPlan.getEndDate()).isEqualTo(beforeSavePlan.getEndDate());
    }

    @Test
    @DisplayName("save plan and flush test")
    public void save_plan_and_flush_test() {
        // when
        Plan savedPlan = planService.saveAndFlush(beforeSavePlan);

        // then
        assertThat(savedPlan.getPlanId()).isEqualTo(planId);
    }

    @Test
    @DisplayName("find plan by id test")
    public void find_plan_by_id_test() {
        // when
        Optional<Plan> foundPlan = planService.findById(planId);

        // then
        assertThat(foundPlan).isPresent();
        assertThat(foundPlan.get()).isEqualTo(plan);
    }

    @Test
    @DisplayName("find plan by id throw exception test")
    public void find_plan_by_id_throw_exception_test() {
        // when
        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> planService.findByIdOrThrow(planId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(PlanExceptionCode.PLAN_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("find latest plan by history id or throw should return plan")
    public void find_latest_plan_by_history_id_or_throw_should_return_plan() {
        // when
        Plan foundPlan = planService.findLatestPlanByHistoryIdOrThrow(historyId);

        // then
        assertThat(foundPlan).isEqualTo(plan);
    }
}
