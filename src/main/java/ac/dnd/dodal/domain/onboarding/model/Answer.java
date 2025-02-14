package ac.dnd.dodal.domain.onboarding.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.user_onboarding.model.UserAnswer;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    private AnswerContent content;

    public Answer(Question question, AnswerContent content) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);
        this.question = question;
        this.content = content;
    }
    
}
