package ac.dnd.dodal.domain.plan.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.domain.plan.constraint.PlanConstraints;

@Entity(name = "plans")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false)
    private Long goalId;

    @Column(nullable = false)
    private Long historyId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isSucceed = false;

    private String guide;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public Plan(Long goalId, Long historyId,
            String title, LocalDateTime startDate, LocalDateTime endDate) {
        validateTitle(title);
        validateDate(startDate, endDate);

        this.goalId = goalId;
        this.historyId = historyId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Plan(Long planId, Long goalId, Long historyId,
            String title, Boolean isSucceed, String guide, 
            LocalDateTime startDate, LocalDateTime endDate,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(createdAt, updatedAt, deletedAt);

        validateTitle(title);
        validateDate(startDate, endDate);

        this.planId = planId;
        this.goalId = goalId;
        this.historyId = historyId;
        this.title = title;
        this.isSucceed = isSucceed;
        this.guide = guide;
        this.startDate = startDate;
        this.endDate = endDate;
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

