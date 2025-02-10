DELETE FROM default_feedback_indicators;

DELETE FROM feedback_questions;

INSERT INTO feedback_questions (question, type, order_number, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', 1, 'QS001');

INSERT INTO default_feedback_indicators (question, type, indicator, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', '우선 순위를 재조정해요.', 'QS001-ID001');
INSERT INTO default_feedback_indicators (question, type, indicator, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', '시간 단축을 시도해볼래요.', 'QS001-ID002');
INSERT INTO default_feedback_indicators (question, type, indicator, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', '계획 정확도를 점검해요.', 'QS001-ID003');
INSERT INTO default_feedback_indicators (question, type, indicator, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', '진행 상황을 더 꼼꼼히 확인해요.', 'QS001-ID004');
INSERT INTO default_feedback_indicators (question, type, indicator, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'SUCCESS', '스스로 패턴을 분석해봐요.', 'QS001-ID005');

INSERT INTO feedback_questions (question, type, order_number, code) 
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', 1, 'QF001');

INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '목표를 더 쉽게 설정해요.', 'QF001-ID001');
INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '구체적인 계획을 설정해요.', 'QF001-ID002');
INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '진행상황을 수시로 확인해요.', 'QF001-ID003');
INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '계획 실행 시간을 좀 더 확보해요.', 'QF001-ID004');
INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '주변 환경을 개선해요.', 'QF001-ID005');