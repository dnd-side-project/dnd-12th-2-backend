INSERT INTO user_guides (user_id, type, content)
VALUES (1, 'USER_TYPE', '목표 중심형');

INSERT INTO user_guides (user_id, type, content)
VALUES (1, 'NEW_GOAL', '진행 상황을 측정할 수 있도록 목표를 세워보세요. 예: \"한 달 동안 영어책 1권 완독\"');

INSERT INTO user_guides (user_id, type, content)
VALUES (1, 'NEW_PLAN', '빠르게 끝낼 것과 중요한 것을 구분해볼까요?');

UPDATE users 
SET refresh_token = 'eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzQwMDAwODg3LCJleHAiOjE3NDA4Nzk3NzF9.B2TGyw8e291ziSz4YE2PBcBXD-zWrHE2ut50Ye5dSQnfY26H20Fi1-riXBU-JBNHTX0O6XSg1PjahZ-x8tD8YA'
WHERE user_id = 2;
