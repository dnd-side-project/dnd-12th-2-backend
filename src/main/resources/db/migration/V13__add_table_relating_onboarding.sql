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
    user_answer_id  BIGINT    NOT NULL AUTO_INCREMENT,
    question_content    VARCHAR(50)    NOT NULL,
    answer_content      VARCHAR(50)    NOT NULL,
    user_id        BIGINT    NOT NULL,
    version        INT       NOT NULL,

    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at     TIMESTAMP NULL,

    PRIMARY KEY (user_answer_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO questions (question_id, question_content, order_number)
VALUES
    (1, '현재 가장 관심 있는 목표 분야를 선택해 주세요.', 1),
    (2, '계획을 세울 때 선호하는 방식을 골라주세요.', 2),
    (3, '그동안 계획 설정 시 어려웠던 점을 선택해 주세요.', 3);

INSERT INTO answers (question_id, answer_id, answer_content)
VALUES
(1, 1, '건강/운동'),
(1, 2, '학업/자기개발'),
(1, 3, '커리어/직업'),
(1, 4, '취미/여가'),


(2, 1, '구체적인 단계별 계획'),
(2, 2, '큰 방향성만 잡기'),
(2, 3, '유연하게 접근하기'),
(2, 4, '체크리스트 활용'),

(3, 1, '목표 구체화'),
(3, 2, '시간 관리'),
(3, 3, '우선순위 결정'),
(3, 4, '실행 동기 부족');