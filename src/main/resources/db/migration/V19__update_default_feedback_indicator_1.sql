DELETE FROM default_feedback_indicators WHERE question = '조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?'
AND type = 'FAILURE' AND indicator = '목표를 더 쉽게 설정해요.';

INSERT INTO default_feedback_indicators (question, type, indicator, code)
VALUES ('조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?', 'FAILURE', '계획을 더 쉽게 설정해요.', 'QF001-ID001');