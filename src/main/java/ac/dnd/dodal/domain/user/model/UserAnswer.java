package ac.dnd.dodal.domain.user.model;

import ac.dnd.dodal.common.model.BaseEntity;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns({
    @JoinColumn(name = "question_id", referencedColumnName = "question_id"),
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
  })
  private Answer answer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public UserAnswer(Answer answer, User user) {
    super(LocalDateTime.now(), LocalDateTime.now(), null);
    this.answer = answer;
    this.user = user;
  }
}
