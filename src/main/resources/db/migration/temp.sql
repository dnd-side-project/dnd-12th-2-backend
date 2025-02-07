INSERT INTO users (user_id, nickname, profile_image_url, device_token, email) 
VALUES (1, 'test1', 'profile_image_url1', 'device_token1', 'test1@test.com');
INSERT INTO users (user_id, nickname, profile_image_url, device_token, email) 
VALUES (2, 'test2', 'profile_image_url2', 'device_token2', 'test2@test.com');
INSERT INTO users (user_id, nickname, profile_image_url, device_token, email) 
VALUES (3, 'test3', 'profile_image_url3', 'device_token3', 'test3@test.com');

INSERT INTO user_guides (user_id, type, content) 
VALUES (1, 'PLAN', 'plan_content1');
INSERT INTO user_guides (user_id, type, content) 
VALUES (1, 'TIME', 'time_content1');
INSERT INTO user_guides (user_id, type, content) 
VALUES (2, 'PLAN', 'plan_content2');
INSERT INTO user_guides (user_id, type, content) 
VALUES (2, 'TIME', 'time_content2');
INSERT INTO user_guides (user_id, type, content) 
VALUES (3, 'PLAN', 'plan_content3');
INSERT INTO user_guides (user_id, type, content) 
VALUES (3, 'TIME', 'time_content3');

INSERT INTO goals (goal_id, user_id, title, is_achieved, created_at, updated_at) 
VALUES (1, 1, 'user1_goal1', TRUE, DATETIME('now', '-1 month'), DATETIME('now', '-1 month'));
INSERT INTO goals (goal_id, user_id, title, is_achieved, created_at, updated_at, deleted_at) 
VALUES (2, 1, 'user1_goal2', TRUE, DATETIME('now', '-1 month'), DATETIME('now', '-1 month'), DATETIME('now', '-1 month'));
INSERT INTO goals (goal_id, user_id, title, is_achieved, created_at, updated_at) 
VALUES (3, 1, 'user1_goal3', TRUE, DATETIME('now', '-1 month'), CURRENT_TIMESTAMP);
INSERT INTO goals (goal_id, user_id, title) 
VALUES (4, 1, 'user1_goal4');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (5, 1, 'user1_goal5');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (6, 2, 'user1_goal6');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (7, 2, 'user1_goal7');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (8, 2, 'user1_goal8');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (9, 3, 'user1_goal9');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (10, 3, 'user2_goal1');
INSERT INTO goals (goal_id, user_id, title) 
VALUES (11, 3, 'user2_goal2');

INSERT INTO plan_histories (plan_id, history_id, goal_id, guide) 
VALUES (1, 1, '{"goal1_history1_guide1"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (2, 1, '{"goal1_history2_guide2"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (3, 1, '{"goal1_history3_guide3"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (4, 1, '{"goal1_history4_guide4"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (5, 1, '{"goal1_history5_guide5"}');

INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (6, 4, '{"goal1_history1_guide1"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (7, 4, '{"goal1_history2_guide2"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (8, 4, '{"goal1_history3_guide3"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (9, 4, '{"goal1_history4_guide4"}');
INSERT INTO plan_histories (history_id, goal_id, guide) 
VALUES (10, 4, '{"goal1_history5_guide5"}');

INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (1, 1, 1, 'history1_goal1_plan1', FAIL, '{"history1_goal1_plan1_guide1"}', DATETIME('now', '-30 days'), DATETIME('now', '-29 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (2, 1, 1, 'history1_goal1_plan2', FAIL, '{"history1_goal1_plan2_guide2"}', DATETIME('now', '-28 days'), DATETIME('now', '-27 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (3, 1, 1, 'history1_goal1_plan3', FAIL, '{"history1_goal1_plan3_guide3"}', DATETIME('now', '-27 days'), DATETIME('now', '-26 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (4, 1, 1, 'history1_goal1_plan4', FAIL, '{"history1_goal1_plan4_guide4"}', DATETIME('now', '-24 days'), DATETIME('now', '-23 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (5, 1, 1, 'history1_goal1_plan5', FAIL, '{"history1_goal1_plan5_guide5"}', DATETIME('now', '-21 days'), DATETIME('now', '-20 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (6, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-18 days'), DATETIME('now', '-17 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (7, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-17 days'), DATETIME('now', '-16 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (8, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-16 days'), DATETIME('now', '-15 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (9, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-15 days'), DATETIME('now', '-14 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (10, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-14 days'), DATETIME('now', '-13 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (11, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-13 days'), DATETIME('now', '-12 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (12, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-12 days'), DATETIME('now', '-11 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (13, 1, 1, 'history1_goal1_plan6', TRUE, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-11 days'), DATETIME('now', '-10 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (14, 1, 1, 'history1_goal1_plan6', FAIL, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-10 days'), DATETIME('now', '-9 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (15, 1, 1, 'history1_goal1_plan6', FAIL, '{"history1_goal1_plan6_guide6"}', DATETIME('now', '-9 days'), DATETIME('now', '-8 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (16, 1, 1, 'history1_goal1_plan7', TRUE, '{"history1_goal1_plan6_guide7"}', DATETIME('now', '-8 days'), DATETIME('now', '-7 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (17, 1, 1, 'history1_goal1_plan7', TRUE, '{"history1_goal1_plan6_guide7"}', DATETIME('now', '-7 days'), DATETIME('now', '-6 days'));

INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (18, 2, 1, 'history2_goal1_plan1', FAIL, '{"history2_goal1_plan1_guide1"}', DATETIME('now', '-30 days'), DATETIME('now', '-29 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (19, 2, 1, 'history2_goal1_plan2', FAIL, '{"history2_goal1_plan2_guide2"}', DATETIME('now', '-28 days'), DATETIME('now', '-27 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (20, 2, 1, 'history2_goal1_plan3', FAIL, '{"history2_goal1_plan3_guide3"}', DATETIME('now', '-27 days'), DATETIME('now', '-26 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (21, 2, 1, 'history2_goal1_plan4', FAIL, '{"history2_goal1_plan4_guide4"}', DATETIME('now', '-24 days'), DATETIME('now', '-23 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (22, 2, 1, 'history2_goal1_plan5', FAIL, '{"history2_goal1_plan5_guide5"}', DATETIME('now', '-21 days'), DATETIME('now', '-20 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (23, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-18 days'), DATETIME('now', '-17 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (24, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-17 days'), DATETIME('now', '-16 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (25, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-16 days'), DATETIME('now', '-15 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (26, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-15 days'), DATETIME('now', '-14 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (27, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-14 days'), DATETIME('now', '-13 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (28, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-13 days'), DATETIME('now', '-12 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (29, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-12 days'), DATETIME('now', '-11 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (30, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-11 days'), DATETIME('now', '-10 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (31, 2, 1, 'history2_goal1_plan6', TRUE, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-10 days'), DATETIME('now', '-9 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (32, 2, 1, 'history2_goal1_plan6', FAIL, '{"history2_goal1_plan6_guide6"}', DATETIME('now', '-9 days'), DATETIME('now', '-8 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (33, 2, 1, 'history2_goal1_plan7', TRUE, '{"history2_goal1_plan6_guide7"}', DATETIME('now', '-8 days'), DATETIME('now', '-7 days'));
INSERT INTO plans (plan_id, history_id, goal_id, title, is_succeed, guide, start_date, end_date) 
VALUES (34, 2, 1, 'history2_goal1_plan7', TRUE, '{"history2_goal1_plan6_guide7"}', DATETIME('now', '-7 days'), DATETIME('now', '-6 days'));

INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (1, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (1, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (1, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (2, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (2, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (2, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (3, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (3, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (4, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (4, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (4, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (5, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (5, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (5, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (6, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (6, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (6, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (7, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (7, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (7, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (8, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (8, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (8, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (9, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (9, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (9, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (10, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (10, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (10, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (11, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (11, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (11, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (12, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (12, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (12, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (13, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (13, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (13, 'question3', 'indicator3');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (14, 'question1', 'indicator1');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (14, 'question2', 'indicator2');
INSERT INTO plan_feedbacks (plan_id, question, indicator) 
VALUES (14, 'question3', 'indicator3');
