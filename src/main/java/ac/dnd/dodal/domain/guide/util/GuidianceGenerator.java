package ac.dnd.dodal.domain.guide.util;

import java.util.List;

import ac.dnd.dodal.common.exception.InternalServerErrorException;
import ac.dnd.dodal.domain.guide.enums.NewGoalGuide;
import ac.dnd.dodal.domain.guide.enums.NewPlanGuide;
import ac.dnd.dodal.domain.guide.enums.UpdatePlanGuide;
import ac.dnd.dodal.domain.guide.enums.UserType;
import ac.dnd.dodal.domain.guide.exception.UserGuideExceptionCode;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingNotFoundException;
import ac.dnd.dodal.domain.user.model.UserAnswer;

public class GuidianceGenerator {

    public static String generateNewGoalGuide(List<UserAnswer> userAnswers) {
        AnswerContent interestGoal = getInterestGoal(userAnswers);

        try {
            NewGoalGuide newGoalGuide = NewGoalGuide.getByInterestGoal(interestGoal);
            return newGoalGuide.getGuide();
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException
                (UserGuideExceptionCode.FAIL_TO_GENERATE_USER_GUIDE, e);
        }
    }

    public static String generateNewPlanGuide(List<UserAnswer> userAnswers) {
        AnswerContent preferenceSetPlan = getPreferenceSetPlan(userAnswers);
        AnswerContent difficultySetPlan = getDifficultySetPlan(userAnswers);

        try {
            NewPlanGuide newPlanGuide = NewPlanGuide.getByPreferenceAndDifficultySetPlan(
                preferenceSetPlan, difficultySetPlan);
            return newPlanGuide.getGuide();
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException(
                UserGuideExceptionCode.FAIL_TO_GENERATE_USER_GUIDE, e);
        }
    }

    public static String generateUpdatePlanGuide(UserType userType, String feedbackIndicator) {
        try {
            UpdatePlanGuide updatePlanGuide = UpdatePlanGuide.getByUserTypeAndFeedbackIndicator(
                userType.name(), feedbackIndicator);

                return updatePlanGuide.getGuide();
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException(
                UserGuideExceptionCode.FAIL_TO_GENERATE_USER_GUIDE, e);
        }
    }

    private static AnswerContent getInterestGoal(List<UserAnswer> userAnswers) {
        UserAnswer answer = userAnswers.stream()
                .filter(userAnswer -> userAnswer.getAnswerContent().startsWith("INTEREST_GOAL"))
                .findFirst().orElseThrow(OnBoardingNotFoundException::new);
        String answerContent = answer.getAnswerContent();

        return AnswerContent.valueOf(answerContent);
    }

    private static AnswerContent getPreferenceSetPlan(List<UserAnswer> userAnswers) {
        UserAnswer answer = userAnswers.stream()
            .filter(userAnswer -> userAnswer.getAnswerContent()
                .startsWith("PREFERENCE_SET_PLAN"))
            .findFirst()
                .orElseThrow(OnBoardingNotFoundException::new);
        String answerContent = answer.getAnswerContent();

        return AnswerContent.valueOf(answerContent);
    }

    private static AnswerContent getDifficultySetPlan(List<UserAnswer> userAnswers) {
        UserAnswer answer = userAnswers.stream()
            .filter(userAnswer -> userAnswer.getAnswerContent()
                .startsWith("DIFFICULTY_SET_PLAN"))
            .findFirst()
                .orElseThrow(OnBoardingNotFoundException::new);
        String answerContent = answer.getAnswerContent();

        return AnswerContent.valueOf(answerContent);
    }
}
