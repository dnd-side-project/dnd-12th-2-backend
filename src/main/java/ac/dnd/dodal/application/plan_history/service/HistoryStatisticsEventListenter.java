package ac.dnd.dodal.application.plan_history.service;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;

@Component
@RequiredArgsConstructor
public class HistoryStatisticsEventListenter {

    private final HistoryStatisticsService historyStatisticsService;

    @EventListener
    public void handlePlanCompletedEvent(PlanCompletedEvent event) {
        HistoryStatistics historyStatistics =
            historyStatisticsService.findByIdOrThrow(event.getHistoryId());

        historyStatistics.incrementCount(event.getStatus());
        historyStatisticsService.save(historyStatistics);
    }
}
