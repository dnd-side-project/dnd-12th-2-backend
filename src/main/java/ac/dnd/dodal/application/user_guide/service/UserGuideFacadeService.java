package ac.dnd.dodal.application.user_guide.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.guide.exception.UserGuideExceptionCode;
import ac.dnd.dodal.application.user_guide.usecase.GetUserGuideUseCase;
import ac.dnd.dodal.ui.user_guide.response.UserGuideResponse;

@Service
@RequiredArgsConstructor
public class UserGuideFacadeService implements GetUserGuideUseCase {

    private final UserGuideService userGuideService;

    @Override
    public UserGuideResponse getUserGuide(Long userId, GuideType guideType) {
        Optional<UserGuide> userGuide = userGuideService.findByUserIdAndType(userId, guideType);

        if (!userGuide.isPresent() && guideType == GuideType.UPDATE_PLAN) {
            return new UserGuideResponse(guideType.getValue(), null);
        }

        return UserGuideResponse.of(userGuide.orElseThrow(() 
            -> new NotFoundException(UserGuideExceptionCode.USER_GUIDE_NOT_FOUND)));
    }
}
