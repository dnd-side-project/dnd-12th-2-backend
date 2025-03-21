package ac.dnd.dodal.domain.guide.util;

import java.util.List;
import java.util.stream.Collectors;

import ac.dnd.dodal.common.exception.InternalServerErrorException;
import ac.dnd.dodal.domain.guide.enums.NewGoalGuide;
import ac.dnd.dodal.domain.guide.enums.NewPlanGuide;
import ac.dnd.dodal.domain.guide.enums.UpdatePlanGuide;
import ac.dnd.dodal.domain.guide.enums.UserType;
import ac.dnd.dodal.domain.guide.exception.UserGuideExceptionCode;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.user.model.UserAnswer;

public class GuidianceGenerator {

    public static String generateUserTypeGuide(List<UserAnswer> userAnswers) {
        AnswerContent preferenceSetPlan = getPreferenceSetPlan(userAnswers);
        AnswerContent difficultySetPlan = getDifficultySetPlan(userAnswers);

        try {
            UserType userType = UserType.getByPreferenceAndDifficultySetPlan(preferenceSetPlan,
                    difficultySetPlan);

            return userType.getValue();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(
                    UserGuideExceptionCode.FAIL_TO_GENERATE_USER_GUIDE, e);
        }
    }

    public static String generateNewGoalGuide(List<UserAnswer> userAnswers) {
        AnswerContent interestGoal = getInterestGoal(userAnswers);

        try {
            NewGoalGuide newGoalGuide = NewGoalGuide.getByInterestGoal(interestGoal);
            return newGoalGuide.getGuide();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new InternalServerErrorException(
                UserGuideExceptionCode.FAIL_TO_GENERATE_USER_GUIDE, e);
        }
    }

    private static AnswerContent getInterestGoal(List<UserAnswer> userAnswers) {
        List<AnswerContent> answerContents = userAnswers.stream()
            .map(answer -> AnswerContent.of(answer.getAnswerContent()))
                .collect(Collectors.toList());

            return answerContents.stream()
                .filter(AnswerContent::isInterestGoal)
                .findFirst()
                    .orElseThrow(() -> 
                    new IllegalArgumentException("Interest Goal not found: " + answerContents));
    }

    private static AnswerContent getPreferenceSetPlan(List<UserAnswer> userAnswers) {
        List<AnswerContent> answerContents = userAnswers.stream()
            .map(answer -> AnswerContent.of(answer.getAnswerContent()))
            .collect(Collectors.toList());

        return answerContents.stream()
            .filter(AnswerContent::isPreferenceSetPlan)
            .findFirst()
                .orElseThrow(() -> 
                new IllegalArgumentException("Preference Set Plan not found: " + answerContents));
    }

    private static AnswerContent getDifficultySetPlan(List<UserAnswer> userAnswers) {
        List<AnswerContent> answerContents = userAnswers.stream()
            .map(answer -> AnswerContent.of(answer.getAnswerContent()))
            .collect(Collectors.toList());
        
        return answerContents.stream()
            .filter(AnswerContent::isDifficultySetPlan)
            .findFirst()
                .orElseThrow(() -> 
                new IllegalArgumentException("Difficulty Set Plan not found: " + answerContents));
    }
}
