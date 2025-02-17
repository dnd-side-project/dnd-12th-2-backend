package ac.dnd.dodal.domain.onboarding.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingExceptionCode;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingNotFoundException;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@IdClass(AnswerId.class)
@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "answers")
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long answerId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer_content", nullable = false)
    private String content;

    public Answer(Question question, AnswerContent content) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);
        this.question = question;
        this.content = content.getContent();
    }

    public AnswerContent getAnswerContentEnum() {
        for (AnswerContent ac : AnswerContent.values()) {
            if (ac.getContent().equals(this.content)) {
                return ac;
            }
        }
        throw new OnBoardingNotFoundException(OnBoardingExceptionCode.NOT_FOUND_ANSWER);
    }
}
