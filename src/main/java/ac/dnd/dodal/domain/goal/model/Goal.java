package ac.dnd.dodal.domain.goal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.domain.goal.constraint.GoalConstraint;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
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

    public void achieve(Long userId) {
        if (this.userId != userId) {
            throw new UnauthorizedException();
        }
        if (this.deletedAt != null) {
            throw new BadRequestException(GoalExceptionCode.DELETED_GOAL);
        }
        this.isAchieved = true;
    }

    public Goal(Long userId, String title) {
        super();

        if (title == null || title.isEmpty()) {
            throw new BadRequestException(GoalExceptionCode.GOAL_TITLE_EMPTY);
        }
        if (title.length() > GoalConstraint.MAX_GOAL_TITLE_LENGTH) {
            throw new BadRequestException(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH);
        }
        this.userId = userId;
        this.title = title;
        this.isAchieved = false;
    }
}
