package ac.dnd.dodal.application.plan.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan_history.PlanHistoryFixture;
import ac.dnd.dodal.domain.plan_history.exception.PlanHistoryExceptionCode;
import ac.dnd.dodal.application.plan.dto.command.*;
import ac.dnd.dodal.application.goal.dto.AddPlanCommandFixture;
import ac.dnd.dodal.application.goal.service.GoalService;
import ac.dnd.dodal.application.plan_history.service.PlanHistoryService;

@ExtendWith(MockitoExtension.class)
public class PlanCommandServiceTest {

    @Mock
    private GoalService goalService;

    @Mock
    private PlanHistoryService planHistoryService;

    @Mock
    private PlanService planService;

    @InjectMocks
    private PlanCommandService planCommandService;

    private Goal goal;
    private PlanHistory planHistory;
    private Plan successPlan;
    private Plan failurePlan;
    private Plan uncompletedPlan;
    private Long userId;
    private Long goalId;
    private Long planHistoryId;
    private Long planId;

    @BeforeEach
    public void setUp() {
        goal = GoalFixture.goal();
        planHistory = PlanHistoryFixture.planHistory();
        successPlan = PlanFixture.successPlan();
        failurePlan = PlanFixture.failurePlan();
        uncompletedPlan = PlanFixture.plan();

        userId = goal.getUserId();
        goalId = goal.getGoalId();
        planHistoryId = planHistory.getHistoryId();
        planId = 1L;

        lenient().when(goalService.findByIdOrThrow(goalId)).thenReturn(goal);
        lenient().when(planHistoryService.findByIdOrThrow(planHistoryId)).thenReturn(planHistory);
    }

    @Test
    @DisplayName("Add new plan success")
    public void add_new_plan_success() {
        // given
        AddNewPlanCommand command =
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(failurePlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(failurePlan));

        // when
        planCommandService.addNewPlan(command);

        // then
        verify(planService).save(any(Plan.class));
    }

    @Test
    @DisplayName("Add new plan failure uncompleted plan")
    public void add_new_plan_failure_uncompleted_plan() {
        // given
        AddNewPlanCommand command =
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(uncompletedPlan));

        // when & then
        assertThatThrownBy(() -> planCommandService.addNewPlan(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(PlanHistoryExceptionCode.PLAN_CAN_BE_ADDED_ONLY_TO_LAST.getMessage());
    }

    @Test
    @DisplayName("Add new plan failure not by latest plan")
    public void add_new_plan_failure_not_by_latest_plan() {
        // given
        AddNewPlanCommand command =
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(uncompletedPlan));

        // when & then
        assertThatThrownBy(() -> planCommandService.addNewPlan(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(PlanHistoryExceptionCode.PLAN_CAN_BE_ADDED_ONLY_TO_LAST.getMessage());
    }

    @Test
    @DisplayName("Add same plan success")
    public void add_same_plan_success() {
        // given
        AddSamePlanCommand command =
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(successPlan));

        // when
        planCommandService.addSamePlan(command);

        // then
        verify(planService).saveAll(ArgumentMatchers.<List<Plan>>any());
    }

    @Test
    @DisplayName("Add same plan failure not by latest plan")
    public void add_same_plan_failure_not_by_latest_plan() {
        // given
        AddSamePlanCommand command =
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(uncompletedPlan));

        // when & then
        assertThatThrownBy(() -> planCommandService.addSamePlan(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(PlanHistoryExceptionCode.PLAN_CAN_BE_ADDED_ONLY_TO_LAST.getMessage());
    }

    @Test
    @DisplayName("Add same plan failure by uncompleted plan")
    public void add_same_plan_failure_by_uncompleted_plan() {
        // given
        AddSamePlanCommand command =
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planHistoryId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.findLatestPlanByHistoryId(command.planHistoryId()))
                .thenReturn(Optional.of(uncompletedPlan));

        // when & then
        assertThatThrownBy(() -> planCommandService.addSamePlan(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(PlanHistoryExceptionCode.PLAN_CAN_BE_ADDED_ONLY_TO_LAST.getMessage());
    }

    @Test
    @DisplayName("Create plan and history success")
    public void create_plan_and_history_success() {
        // given
        CreatePlanAndHistoryCommand command =
                AddPlanCommandFixture.createPlanAndHistoryCommand(userId, goalId);
        when(planHistoryService.saveAndFlush(any(PlanHistory.class))).thenReturn(planHistory);

        // when
        planCommandService.createPlanAndHistory(command);

        // then
        verify(planService).save(any(Plan.class));
    }
}
