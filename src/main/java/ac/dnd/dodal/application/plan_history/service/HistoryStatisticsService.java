package ac.dnd.dodal.application.plan_history.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.plan_history.exception.PlanHistoryExceptionCode;
import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;
import ac.dnd.dodal.application.plan_history.repository.HistoryStatisticsRepository;

@Service
@RequiredArgsConstructor
public class HistoryStatisticsService {

    private final HistoryStatisticsRepository historyStatisticsRepository;

    public HistoryStatistics save(HistoryStatistics historyStatistics) {
        return historyStatisticsRepository.save(historyStatistics);
    }

    public HistoryStatistics findByIdOrThrow(Long id) {
        return historyStatisticsRepository.findById(id)
            .orElseThrow(() -> 
                new NotFoundException(PlanHistoryExceptionCode.HISTORY_STATISTICS_NOT_FOUND));
    }
}

