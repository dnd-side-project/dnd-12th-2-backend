package ac.dnd.dodal.domain.plan.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.domain.plan.constraint.PlanConstraints;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.guide.service.GuidianceService;

@Entity(name = "plans")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_history_id", nullable = false)
    private PlanHistory history;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlanFeedback> feedbacks;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private PlanStatus status = PlanStatus.NONE;

    private String guide;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public void complete(PlanStatus status, List<PlanFeedback> feedbacks) {
        if (this.deletedAt != null) {
            throw new ForbiddenException(PlanExceptionCode.PLAN_ALREADY_DELETED);
        }
        if (this.isCompleted()) {
            throw new BadRequestException(PlanExceptionCode.PLAN_ALREADY_COMPLETED);
        }
        if (this.startDate.isAfter(LocalDateTime.now())) {
            throw new BadRequestException(PlanExceptionCode.PLAN_SUCCEED_AFTER_START_DATE);
        }
        if (feedbacks == null || feedbacks.size() == 0) {
            throw new BadRequestException(PlanExceptionCode.REQUIRED_FEEDBACK);
        }
        this.status = status;
        this.feedbacks = feedbacks;
        this.guide = GuidianceService.generateGuide(feedbacks);
    }

    public Plan(String title, LocalDateTime startDate, LocalDateTime endDate) {
        validateTitle(title);
        validateDate(startDate, endDate);

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Plan(Long planId, Goal goal, PlanHistory history,
            String title, PlanStatus status, String guide, 
            LocalDateTime startDate, LocalDateTime endDate,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(createdAt, updatedAt, deletedAt);

        validateTitle(title);
        validateDate(startDate, endDate);

        this.planId = planId;
        this.goal = goal;
        this.history = history;
        this.title = title;
        this.status = status;
        this.guide = guide;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setHistory(PlanHistory history) {
        this.history = history;
    }

    public boolean isSucceeded() {
        return this.status == PlanStatus.SUCCESS;
    }

    public boolean isCompleted() {
        return this.status != PlanStatus.NONE;
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new BadRequestException(PlanExceptionCode.PLAN_TITLE_EMPTY);
        }
        if (title.length() > PlanConstraints.MAX_PLAN_TITLE_LENGTH) {
            throw new BadRequestException(PlanExceptionCode.PLAN_TITLE_EXCEED_MAX_LENGTH);
        }
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadRequestException(PlanExceptionCode.PLAN_START_DATE_AFTER_END_DATE);
        }
    }
}
