package ac.dnd.dodal.domain.onboarding.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.util.AnswerContentConverter;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @Convert(converter = AnswerContentConverter.class)
    private AnswerContent content;

    public Answer(Question question, AnswerContent content) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);
        this.question = question;
        this.content = content;
    }
    
}
