package ac.dnd.dodal.domain.user.model;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.util.AnswerContentConverter;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.onboarding.model.Answer;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_answers")
public class UserAnswer extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_answer_id", nullable = false)
  private Long id;

  @Column(name = "question_content", nullable = false)
  private String questionContent;

  @Column(name = "answer_content", nullable = false)
  @Convert(converter = AnswerContentConverter.class)
  private AnswerContent answerContent;

  @Column(name = "version", nullable = false)
  private int version;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public UserAnswer(String questionContent, AnswerContent answerContent, User user, int version) {
    super(LocalDateTime.now(), LocalDateTime.now(), null);
    this.questionContent = questionContent;
    this.answerContent = answerContent;
    this.user = user;
    this.version = version;
  }
}
