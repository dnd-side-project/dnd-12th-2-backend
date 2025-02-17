CREATE TABLE IF NOT EXISTS goal_statistics (
    goal_id BIGINT NOT NULL,
    success_count INT NOT NULL DEFAULT 0,
    failure_count INT NOT NULL DEFAULT 0,

    PRIMARY KEY (goal_id),
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id),
    CONSTRAINT valid_goal_statistics_count CHECK (success_count >= 0 AND failure_count >= 0)
);

CREATE TABLE IF NOT EXISTS history_statistics (
    history_id BIGINT NOT NULL,
    success_count INT NOT NULL DEFAULT 0,
    failure_count INT NOT NULL DEFAULT 0,

    PRIMARY KEY (history_id),
    FOREIGN KEY (history_id) REFERENCES plan_histories(history_id),
    CONSTRAINT valid_history_statistics_count CHECK (success_count >= 0 AND failure_count >= 0)
);
