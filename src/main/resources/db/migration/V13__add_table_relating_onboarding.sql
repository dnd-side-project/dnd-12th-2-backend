CREATE TABLE IF NOT EXISTS questions
(
    question_id      BIGINT AUTO_INCREMENT,
    question_content VARCHAR(50) NOT NULL,
    order_number     INT         NOT NULL,

    created_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at       TIMESTAMP   NULL,

    PRIMARY KEY (question_id)
);

CREATE TABLE IF NOT EXISTS answers
(
    answer_id   BIGINT      NOT NULL AUTO_INCREMENT,
    question_id BIGINT      NOT NULL,
    answer_content     VARCHAR(50) NOT NULL,

    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP   NULL,

    PRIMARY KEY (question_id, answer_id),
    KEY idx_answers_answer_id (answer_id),
    FOREIGN KEY (question_id) REFERENCES questions (question_id)
);

CREATE TABLE IF NOT EXISTS user_answers
(
    user_answer_id BIGINT    NOT NULL AUTO_INCREMENT,
    question_id    BIGINT    NOT NULL,
    answer_id      BIGINT    NOT NULL,
    user_id        BIGINT    NOT NULL,

    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at     TIMESTAMP NULL,

    PRIMARY KEY (user_answer_id),
    FOREIGN KEY (question_id, answer_id)
        REFERENCES answers(question_id, answer_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO questions (question_id, question_content, order_number, created_at, updated_at)
VALUES
    (1, 'INTEREST_GOAL', 1, '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
    (2, 'PREFERENCE_SET_PLAN', 2, '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
    (3, 'DIFFICULTY_SET_PLAN', 3, '2025-02-13 00:00:00', '2025-02-13 00:00:00');

INSERT INTO answers (question_id, answer_id, answer_content, created_at, updated_at)
VALUES
(1, 1, 'INTEREST_GOAL_1', '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(1, 2, 'INTEREST_GOAL_2',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(1, 3, 'INTEREST_GOAL_3',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(1, 4, 'INTEREST_GOAL_4',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),


(2, 1, 'PREFERENCE_SET_PLAN_1', '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(2, 2, 'PREFERENCE_SET_PLAN_2',     '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(2, 3, 'PREFERENCE_SET_PLAN_3',     '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(2, 4, 'PREFERENCE_SET_PLAN_4',     '2025-02-13 00:00:00', '2025-02-13 00:00:00'),

(3, 1, 'DIFFICULTY_SET_PLAN_1',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(3, 2, 'DIFFICULTY_SET_PLAN_2',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(3, 3, 'DIFFICULTY_SET_PLAN_3',  '2025-02-13 00:00:00', '2025-02-13 00:00:00'),
(3, 4, 'DIFFICULTY_SET_PLAN_4',  '2025-02-13 00:00:00', '2025-02-13 00:00:00');