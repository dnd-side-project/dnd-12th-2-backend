package ac.dnd.dodal.application.goal.service;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.goal.usecase.AddPlanUseCase;
import ac.dnd.dodal.application.goal.usecase.CreatePlanAndHistoryUseCase;
import ac.dnd.dodal.application.plan.dto.command.AddSamePlanCommand;
import ac.dnd.dodal.application.plan.dto.command.AddNewPlanCommand;
import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;

@Service
public class GoalPlanCommandService implements AddPlanUseCase, CreatePlanAndHistoryUseCase {

    @Override
    public Long addSamePlan(AddSamePlanCommand command) {
        return null;
    }

    @Override
    public Long addNewPlan(AddNewPlanCommand command) {
        return null;
    }

    @Override
    public Long createPlanAndHistory(CreatePlanAndHistoryCommand command) {
        return null;
    }
}
