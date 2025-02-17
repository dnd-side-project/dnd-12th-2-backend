package ac.dnd.dodal.domain.onboarding.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.onboarding.enums.QuestionContent;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingExceptionCode;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingNotFoundException;
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

    @Column(name = "question_main_content", nullable = false)
    private String mainContent;

    @Column(name = "question_sub_content", nullable = false)
    private String subContent;

    @Column(name="order_number", columnDefinition = "question order")
    private int order;

    public Question(QuestionContent mainContent, int order) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);

        this.mainContent = mainContent.getMainContent();
        this.subContent = mainContent.getSubContent();
        this.order = order;
    }

    public QuestionContent getQuestionContentEnum() {
        for (QuestionContent qc : QuestionContent.values()) {
            if (qc.getMainContent().equals(this.mainContent) &&
                    qc.getSubContent().equals(this.subContent)) {
                return qc;
            }
        }
    throw new OnBoardingNotFoundException(OnBoardingExceptionCode.NOT_FOUND_Question);
    }

}
