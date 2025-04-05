package ac.dnd.dodal.application.plan_history.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

import java.util.List;

@Repository
public interface PlanHistoryRepository extends JpaRepository<PlanHistory, Long> {

    @Query("SELECT new ac.dnd.dodal.ui.plan_history.response.HistoryResponse("
            + "hs.historyId, "
            + "hs.recentCompletedPlanTitle, "
            + "hs.successCount, "
            + "hs.failureCount, "
            + "hs.totalCount) "
            + "FROM history_statistics hs "
            + "JOIN plan_histories ph ON hs.historyId = ph.historyId "
            + "WHERE ph.goal.goalId = :goalId "
            + "ORDER BY hs.totalCount DESC")
    Page<HistoryResponse> getHistoryResponsesByGoalId(Long goalId, Pageable pageable);

    @Query("SELECT new ac.dnd.dodal.ui.plan_history.response.HistoryResponse("
            + "hs.historyId, "
            + "hs.recentCompletedPlanTitle, "
            + "hs.successCount, "
            + "hs.failureCount, "
            + "hs.totalCount) "
            + "FROM history_statistics hs "
            + "JOIN plan_histories ph ON hs.historyId = ph.historyId "
            + "WHERE ph.goal.goalId = :goalId "
            + "AND hs.historyId = :historyId")
    HistoryResponse getHistoryResponseByGoalId(Long goalId, Long historyId);

    @Query("SELECT ph FROM plan_histories ph WHERE ph.goal.goalId = :goalId")
    List<PlanHistory> findAllByGoalId(Long goalId);
}
