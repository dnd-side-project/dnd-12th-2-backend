UPDATE plans
SET completed_date = CASE
    WHEN status = 'NONE' THEN NULL
    WHEN status IN ('SUCCESS', 'FAILURE') THEN end_date
    ELSE completed_date
END;