ALTER TABLE plans ADD COLUMN completed_time TIMESTAMP;

UPDATE plans
SET completed_time = CASE
    WHEN status = 'NONE' THEN NULL
    WHEN status IN ('SUCCESS', 'FAILURE') THEN end_date
    ELSE completed_time
END;
