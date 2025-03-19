package ac.dnd.dodal.application.plan.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import ac.dnd.dodal.ui.plan.response.PlanElement;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationEventPublisher;

import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.enums.UserType;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;
import ac.dnd.dodal.domain.plan.event.DeletedPlanEvent;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan_history.PlanHistoryFixture;
import ac.dnd.dodal.domain.plan_history.exception.PlanHistoryExceptionCode;
import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;
import ac.dnd.dodal.application.plan.dto.command.*;
import ac.dnd.dodal.application.plan_feedback.service.PlanFeedbackService;
import ac.dnd.dodal.application.plan.dto.CompletePlanCommandFixture;
import ac.dnd.dodal.application.goal.dto.AddPlanCommandFixture;
import ac.dnd.dodal.application.goal.service.GoalService;
import ac.dnd.dodal.application.plan_history.service.PlanHistoryService;
import ac.dnd.dodal.application.user_guide.service.UserGuideService;
import ac.dnd.dodal.application.plan_history.service.HistoryStatisticsService;

@ExtendWith(MockitoExtension.class)
public class PlanCommandServiceTest {

    @Mock
    private GoalService goalService;

    @Mock
    private PlanHistoryService planHistoryService;

    @Mock
    private PlanService planService;

    @Mock
    private PlanFeedbackService planFeedbackService;

    @Mock
    private UserGuideService userGuideService;

    @Mock
    private HistoryStatisticsService historyStatisticsService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private PlanCommandService planCommandService;

    private Goal goal;
    private PlanHistory planHistory;
    private Plan successPlan;
    private Plan failurePlan;
    private Plan uncompletedPlan;
    private Plan deletedPlan;
    private Plan notStartedPlan;

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
        deletedPlan = PlanFixture.deletedPlan();
        notStartedPlan = PlanFixture.tommorowStartPlan();

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
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(failurePlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
                AddPlanCommandFixture.addNewPlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
                AddPlanCommandFixture.addSamePlanCommand(userId, goalId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.findLatestPlanByHistoryId(planHistory.getHistoryId()))
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
        verify(historyStatisticsService).save(any(HistoryStatistics.class));
    }

    @DisplayName("Complete success plan success")
    public void complete_success_plan_success() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.successPlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(goal);
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.save(any(Plan.class))).thenReturn(uncompletedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when
        List<PlanElement> savedPlan = planCommandService.completePlan(command);

        // then
        verify(planFeedbackService).saveAll(anyList());
        verify(planService).save(any(Plan.class));
        verify(eventPublisher).publishEvent(any(PlanCompletedEvent.class));
        assertThat(savedPlan.getLast().status()).isEqualTo(PlanStatus.SUCCESS);
    }

    @DisplayName("Complete failure plan success")
    public void complete_failure_plan_success() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(goal);
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.save(any(Plan.class))).thenReturn(uncompletedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when
        List<PlanElement> savedPlan = planCommandService.completePlan(command);

        // then
        verify(planFeedbackService).saveAll(anyList());
        verify(planService).save(any(Plan.class));
        verify(eventPublisher).publishEvent(any(PlanCompletedEvent.class));
        assertThat(savedPlan.getLast().status()).isEqualTo(PlanStatus.FAILURE);
    }
    
    @DisplayName("Complete failure plan failure by achieved goal")
    public void complete_failure_plan_failure_by_achieved_goal() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(GoalFixture.achievedGoal());
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.save(any(Plan.class))).thenReturn(uncompletedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when & then
        assertThatThrownBy(() -> planCommandService.completePlan(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(GoalExceptionCode.ACHIEVED_GOAL.getMessage());
    }

    @DisplayName("Complete failure plan failure by deleted goal")
    public void complete_failure_plan_failure_by_deleted_goal() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(GoalFixture.deletedGoal());
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(uncompletedPlan);
        when(planService.save(any(Plan.class))).thenReturn(uncompletedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when & then
        assertThatThrownBy(() -> planCommandService.completePlan(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(GoalExceptionCode.DELETED_GOAL.getMessage());
    }

    @DisplayName("Complete failure plan failure by already completed plan")
    public void complete_failure_plan_failure_by_already_completed_plan() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(goal);
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when & then
        assertThatThrownBy(() -> planCommandService.completePlan(command))
                        .isInstanceOf(ForbiddenException.class)
                        .hasMessage(PlanExceptionCode.PLAN_ALREADY_COMPLETED.getMessage());
    }
    
    @DisplayName("Complete failure plan failure by already deleted plan")
    public void complete_failure_plan_failure_by_already_deleted_plan() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType = new UserGuide
            (userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(goal);
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(deletedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
            .thenReturn(userType);

        // when & then
        assertThatThrownBy(() -> planCommandService.completePlan(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(PlanExceptionCode.PLAN_ALREADY_DELETED.getMessage());
    }

    @DisplayName("Complete failure plan failure by plan not started")
    public void complete_failure_plan_failure_by_plan_not_started() {
        // given
        CompletePlanCommand command = CompletePlanCommandFixture.failurePlanCommand();
        UserGuide userType =
                new UserGuide(userId, GuideType.USER_TYPE, UserType.GOAL_ORIENTED.getValue());
        uncompletedPlan.setGoal(goal);
        uncompletedPlan.setHistory(planHistory);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(notStartedPlan);
        when(userGuideService.findByUserIdAndTypeOrThrow(userId, GuideType.USER_TYPE))
                .thenReturn(userType);

        // when & then
        assertThatThrownBy(() -> planCommandService.completePlan(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(PlanExceptionCode.PLAN_SUCCEED_AFTER_START_DATE.getMessage());
    }

    @Test
    @DisplayName("Delete plan success")
    public void delete_plan_success() {
        // given
        DeletePlanCommand command = new DeletePlanCommand(userId, planId);
        when(planService.findByIdOrThrow(command.planId())).thenReturn(successPlan);

        // when
        planCommandService.delete(command);

        // then
        verify(planService).save(any(Plan.class));
        verify(eventPublisher).publishEvent(any(DeletedPlanEvent.class));
    }
}
