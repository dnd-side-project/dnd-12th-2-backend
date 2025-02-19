package ac.dnd.dodal.ui.feedback.fixture;

import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

public class FeedbackUIFixture {

    public static CreateFeedbackRequest createFeedbackRequest() {
        return new CreateFeedbackRequest("조금 더 수월하게 달성하기 위해 뭘 해야할까요?", "우선순위를 재조정해요.");
    }
}
