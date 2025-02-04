INSERT IGNORE INTO feedback_questions (question, type, order_number, code) 
VALUES ('계획이 안 된 이유는 무엇인가요?', 'FAILURE', 1, 'QF001');
INSERT IGNORE INTO feedback_questions (question, type, order_number, code) 
VALUES ('계획을 하면서 어떤 게 가장 힘들었나요?', 'FAILURE', 2, 'QF002');
INSERT IGNORE INTO feedback_questions (question, type, order_number, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', 'FAILURE', 3, 'QF003');
INSERT IGNORE INTO feedback_questions (question, type, order_number, code) 
VALUES ('계획이 잘 된 이유가 무엇인가요?', 'SUCCESS', 1, 'QS001');
INSERT IGNORE INTO feedback_questions (question, type, order_number, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', 'SUCCESS', 2, 'QS002');

INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획이 안 된 이유는 무엇인가요?', '목표가 너무 어려웠어요.', 'QF001-ID001');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획이 안 된 이유는 무엇인가요?', '시간이 부족했어요', 'QF001-ID002');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획이 안 된 이유는 무엇인가요?', '의지가 부족했어요.', 'QF001-ID003');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획이 안 된 이유는 무엇인가요?', '계획이 애매했어요.', 'QF001-ID004');

INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획을 하면서 어떤 게 힘들었나요?', '집중하기 어려웠어요', 'QF002-ID001');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획을 하면서 어떤 게 힘들었나요?', '시작하는 게 힘들었어요.', 'QF002-ID002');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획을 하면서 어떤 게 힘들었나요?', '진행 상황을 확인하기 어려웠어요.', 'QF002-ID003');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('계획을 하면서 어떤 게 힘들었나요?', '예상보다 시간이 많이 걸렸어요.', 'QF002-ID004');

INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '목표를 더 쉬운 걸로 설정하기', 'QF003-ID001');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '계획을 더 구체적으로 세우기', 'QF003-ID002');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '중간에 진행 상황 확인하기', 'QF003-ID003');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code) 
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '실행 시간을 미리 확보하기', 'QF003-ID004');

INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('계획이 잘 된 이유가 무엇인가요?', '목표가 쉬웠어요.', 'QS001-ID001');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('계획이 잘 된 이유가 무엇인가요?', '꾸준히 노력했어요.', 'QS001-ID002');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('계획이 잘 된 이유가 무엇인가요?', '필요한 도움을 받았어요.', 'QS001-ID003');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('계획이 잘 된 이유가 무엇인가요?', '계획이 구체적이었어요.', 'QS001-ID004');

INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '목표를 더 쉬운 걸로 설정하기', 'QS002-ID001');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '계획을 더 구체적으로 세우기', 'QS002-ID002');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '중간에 진행 상황 확인하기', 'QS002-ID003');
INSERT IGNORE INTO default_feedback_indicators (question, indicator, code)
VALUES ('다음에 더 잘하기 위해 무엇을 바꾸고 싶나요?', '실행 시간을 미리 확보하기', 'QS002-ID004');