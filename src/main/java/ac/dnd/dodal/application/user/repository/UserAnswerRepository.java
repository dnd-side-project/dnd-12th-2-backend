package ac.dnd.dodal.application.user.repository;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.domain.user.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    @Query("SELECT ua FROM UserAnswer ua WHERE ua.user = :user AND ua.deletedAt IS NULL")
    List<UserAnswer> findAllByUser(User user);

    @Query("SELECT ua FROM UserAnswer ua WHERE ua.user = :user")
    List<UserAnswer> findAllByUserWithDeleted(User user);
}
