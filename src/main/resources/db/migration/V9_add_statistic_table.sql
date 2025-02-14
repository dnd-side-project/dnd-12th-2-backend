CREATE TABLE IF NOT EXISTS goal_statistics (
    goal_id INT NOT NULL,
    success_count INT NOT NULL DEFAULT 0,
    failure_count INT NOT NULL DEFAULT 0,

    PRIMARY KEY (goal_id),
    FOREIGN KEY (goal_id) REFERENCES goals(id),
    CONSTRAINT valid_count CHECK (success_count >= 0 AND failure_count >= 0)
);

CREATE TABLE IF NOT EXISTS history_statistics (
    history_id INT NOT NULL,
    success_count INT NOT NULL DEFAULT 0,
    failure_count INT NOT NULL DEFAULT 0,

    PRIMARY KEY (history_id),
    FOREIGN KEY (history_id) REFERENCES histories(id),
    CONSTRAINT valid_count CHECK (success_count >= 0 AND failure_count >= 0)
);