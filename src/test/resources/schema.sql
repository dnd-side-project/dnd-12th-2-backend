CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT AUTO_INCREMENT,
    nickname VARCHAR(20) NOT NULL,
    profile_image_url VARCHAR(255) NULL,
    device_token VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (user_id),
    CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS user_guides (
    user_id BIGINT,
    `type` VARCHAR(20) NOT NULL,
    content VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (user_id, type),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT guide_type_check CHECK (type IN ('PLAN', 'TIME'))
);

CREATE TABLE IF NOT EXISTS goals (
    goal_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    title VARCHAR(20) NOT NULL,
    is_achieved BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (goal_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS plan_histories (
    history_id BIGINT AUTO_INCREMENT,
    goal_id BIGINT,
    guide JSON NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (history_id),
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id)
);

CREATE TABLE IF NOT EXISTS plans (
    plan_id BIGINT AUTO_INCREMENT,
    history_id BIGINT,
    goal_id BIGINT,

    title VARCHAR(30) NOT NULL,
    is_succeed BOOLEAN NOT NULL DEFAULT FALSE,
    guide VARCHAR(255) NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (plan_id),
    FOREIGN KEY (history_id) REFERENCES plan_histories(history_id),
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id)
);

CREATE TABLE IF NOT EXISTS plan_feedbacks (
    plan_id BIGINT,
    question VARCHAR(255) NOT NULL,
    indicator VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,

    PRIMARY KEY (plan_id, question),
    FOREIGN KEY (plan_id) REFERENCES plans(plan_id)
);

CREATE TABLE IF NOT EXISTS feedback_questions (
    question VARCHAR(50) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    order_number INT NOT NULL,
    code VARCHAR(5) NOT NULL,

    PRIMARY KEY (question, type),
    CONSTRAINT question_code_unique UNIQUE (code),
    CONSTRAINT question_type_check CHECK (type IN ('SUCCESS', 'FAILURE'))
);

CREATE TABLE IF NOT EXISTS default_feedback_indicators (
    question VARCHAR(50) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    indicator VARCHAR(255) NOT NULL,
    code VARCHAR(11) NOT NULL,

    PRIMARY KEY (question, `type`, indicator),
    FOREIGN KEY (question, `type`) REFERENCES feedback_questions(question, `type`),
    CONSTRAINT indicator_code_unique UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS user_feedback_indicators (
    question VARCHAR(50) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    indicator VARCHAR(255) NOT NULL,
    user_id BIGINT,

    PRIMARY KEY (question, `type`, indicator, user_id),
    FOREIGN KEY (question, `type`) REFERENCES feedback_questions(question, `type`),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);