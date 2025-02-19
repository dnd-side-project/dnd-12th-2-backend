package ac.dnd.dodal.application.user_guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;

public interface UserGuideRepository extends JpaRepository<UserGuide, Long> {

    List<UserGuide> findAllByUserId(Long userId);

    Optional<UserGuide> findByUserIdAndType(Long userId, GuideType type);
}
