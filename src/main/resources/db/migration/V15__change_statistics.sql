ALTER TABLE history_statistics
ADD COLUMN total_count INT DEFAULT 0;
ALTER TABLE history_statistics
ADD COLUMN recent_completed_plan_title VARCHAR(50) DEFAULT NULL;

UPDATE history_statistics
SET total_count = success_count + failure_count,
    recent_completed_plan_title = (
        SELECT p.title
        FROM plans p
        WHERE p.history_id = history_id
        ORDER BY p.completed_date DESC
        LIMIT 1
    );

ALTER TABLE goal_statistics
ADD COLUMN total_count INT DEFAULT 0;

UPDATE goal_statistics
SET total_count = success_count + failure_count;