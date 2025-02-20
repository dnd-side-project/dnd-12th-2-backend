package ac.dnd.dodal.application.user_guide.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.guide.exception.UserGuideExceptionCode;
import ac.dnd.dodal.application.user_guide.repository.UserGuideRepository;

@Service
@RequiredArgsConstructor
public class UserGuideService {
    
    private final UserGuideRepository userGuideRepository;

    public void save(UserGuide userGuide) {
        userGuideRepository.save(userGuide);
    }

    public void saveAll(List<UserGuide> userGuides) {
        userGuideRepository.saveAll(userGuides);
    }

    public List<UserGuide> findAllByUserId(Long userId) {
        return userGuideRepository.findAllByUserId(userId);
    }

    public Optional<UserGuide> findByUserIdAndType(Long userId, GuideType type) {
        return userGuideRepository.findByUserIdAndType(userId, type);
    }

    public UserGuide findByUserIdAndTypeOrThrow(Long userId, GuideType type) {
        return userGuideRepository.findByUserIdAndType(userId, type)
            .orElseThrow(() -> new NotFoundException(UserGuideExceptionCode.USER_GUIDE_NOT_FOUND));
    }

    public void delete(Long userId, GuideType type) {
        UserGuide userGuide = findByUserIdAndTypeOrThrow(userId, type);

        delete(userGuide);
    }

    public void delete(UserGuide userGuide) {
        userGuide.delete();
        userGuideRepository.save(userGuide);
    }

    public void deleteAll(Long userId) {
        List<UserGuide> userGuides = findAllByUserId(userId);

        userGuides.forEach(this::delete);
    }
}
