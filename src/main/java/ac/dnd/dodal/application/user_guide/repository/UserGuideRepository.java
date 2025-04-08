package ac.dnd.dodal.application.user_guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.guide.model.UserGuideId;
import org.springframework.data.jpa.repository.Query;

public interface UserGuideRepository extends JpaRepository<UserGuide, UserGuideId> {

    @Query("SELECT ug FROM user_guides ug WHERE ug.userId = :userId AND ug.deletedAt IS NULL")
    List<UserGuide> findAllByUserId(Long userId);

    Optional<UserGuide> findByUserIdAndType(Long userId, GuideType type);
}
