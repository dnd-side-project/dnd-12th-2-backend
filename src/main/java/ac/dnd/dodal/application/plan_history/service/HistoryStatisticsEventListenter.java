package ac.dnd.dodal.application.plan_history.service;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;
import ac.dnd.dodal.domain.plan.event.DeletedPlanEvent;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;

@Component
@RequiredArgsConstructor
public class HistoryStatisticsEventListenter {

    private final HistoryStatisticsService historyStatisticsService;

    @EventListener
    public void handlePlanCompletedEvent(PlanCompletedEvent event) {
        HistoryStatistics historyStatistics =
                historyStatisticsService.findByIdOrThrow(event.getHistoryId());

        historyStatistics.incrementCount(event.getStatus());
        historyStatistics.setRecentCompletedPlanTitle(event.getTitle());
        historyStatisticsService.save(historyStatistics);
    }

    @Async
    @EventListener
    public void handleDeletedPlanEvent(DeletedPlanEvent event) {
        if (event.getStatus() == PlanStatus.NONE) {
            return;
        }
        HistoryStatistics historyStatistics =
                historyStatisticsService.findByIdOrThrow(event.getHistoryId());

        // TODO: 가장 최근 완료된 Plan인 경우 title도 다시 바꿔야한다...
        historyStatistics.decrementCount(event.getStatus());
        historyStatisticsService.save(historyStatistics);
    }
}
