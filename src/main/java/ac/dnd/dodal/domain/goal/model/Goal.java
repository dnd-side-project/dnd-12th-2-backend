package ac.dnd.dodal.domain.goal.model;

import java.util.List;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.goal.constraint.GoalConstraints;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan.model.Plan;

@Entity(name = "goals")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Goal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isAchieved;

    @OneToMany(mappedBy = "goalId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlanHistory> histories;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plan> plans;

    public void addPlan(Long userId, Plan plan) {
        validateAuthor(userId);
        validateGoal();

        plan.setGoal(this);
        this.plans.add(plan);
    }

    public void addHistory(Long userId, PlanHistory history) {
        validateAuthor(userId);
        validateGoal();

        history.setGoal(this);
        this.histories.add(history);
    }

    public void succeedPlan(Long userId, Plan plan, String guide) {
        validateAuthor(userId);
        validateGoal();

        plan.succeed(guide);
    }

    public void achieve(Long userId) {
        validateAuthor(userId);
        validateDeleted();
        if (this.isAchieved) {
            throw new ForbiddenException(GoalExceptionCode.GOAL_ALREADY_ACHIEVED);
        }

        this.isAchieved = true;
    }

    public void delete(Long userId) {
        validateAuthor(userId);
        if (this.deletedAt != null) {
            throw new ForbiddenException(GoalExceptionCode.GOAL_ALREADY_DELETED);
        }

        if (this.histories != null) {
            this.histories.forEach(PlanHistory::delete);
        }
        super.delete();
    }

    public Goal(Long userId, String title) {
        super();

        if (userId == null) {
            throw new UnauthorizedException();
        }
        validateTitle(title);
        this.userId = userId;
        this.title = title;
        this.isAchieved = false;
    }

    public Goal(Long goalId, Long userId, String title, Boolean isAchieved,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(createdAt, updatedAt, deletedAt);

        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.isAchieved = isAchieved;
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new BadRequestException(GoalExceptionCode.GOAL_TITLE_EMPTY);
        }
        if (title.length() > GoalConstraints.MAX_GOAL_TITLE_LENGTH) {
            throw new BadRequestException(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH);
        }
    }

    private void validateAuthor(Long userId) {
        if (this.userId != userId) {
            throw new UnauthorizedException();
        }
    }

    private void validateGoal() {
        validateDeleted();
        validateAchieve();
    }

    private void validateAchieve() {
        if (this.isAchieved) {
            throw new ForbiddenException(GoalExceptionCode.ACHIEVED_GOAL);
        }
    }

    private void validateDeleted() {
        if (this.deletedAt != null) {
            throw new ForbiddenException(GoalExceptionCode.DELETED_GOAL);
        }
    }
}
