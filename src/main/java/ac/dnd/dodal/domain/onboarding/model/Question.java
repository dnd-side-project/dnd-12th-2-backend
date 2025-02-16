package ac.dnd.dodal.domain.onboarding.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.onboarding.enums.QuestionContent;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "questions")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(name = "question_content", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionContent questionContent;

    @Column(name="order_number", columnDefinition = "question order")
    private int order;

    public Question(QuestionContent questionContent, int order) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);

        this.questionContent = questionContent;
        this.order = order;
    }

}
