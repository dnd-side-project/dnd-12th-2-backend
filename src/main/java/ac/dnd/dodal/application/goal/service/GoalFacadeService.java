package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.dnd.dodal.application.goal.dto.command.CreateGoalAndPlanCommand;
import ac.dnd.dodal.application.goal.dto.command.CreateGoalCommand;
import ac.dnd.dodal.application.goal.usecase.CreateGoalAndPlanUseCase;
import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;
import ac.dnd.dodal.application.plan.service.PlanCommandService;

@Service
@RequiredArgsConstructor
public class GoalFacadeService implements CreateGoalAndPlanUseCase {

    private final GoalCommandService goalCommandService;
    private final PlanCommandService planCommandService;

    @Override
    @Transactional
    public Long createGoalAndPlan(CreateGoalAndPlanCommand command) {
        CreateGoalCommand goalCommand = new CreateGoalCommand(command.userId(), command.goalTitle());
        Long goalId = goalCommandService.create(goalCommand);

        CreatePlanAndHistoryCommand planCommand = new CreatePlanAndHistoryCommand(
                command.userId(), goalId, command.planTitle(), command.startDate(), command.endDate());
        planCommandService.createPlanAndHistory(planCommand);

        return goalId;
    }
}
