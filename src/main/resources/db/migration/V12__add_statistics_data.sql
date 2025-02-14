
-- goal_statistics 테이블에 데이터 삽입
INSERT INTO goal_statistics (goal_id, success_count, failure_count)
SELECT goal_id, 0, 0 FROM goals;

-- history_statistics 테이블에 데이터 삽입
INSERT INTO history_statistics (history_id, success_count, failure_count)
SELECT history_id, 0, 0 FROM plan_histories;

-- plan 테이블에서 값을 읽어와서 업데이트하는 쿼리 (예시)
UPDATE goal_statistics
SET success_count = (
        SELECT COUNT(*) FROM plans 
        WHERE goal_id = goal_statistics.goal_id AND status = 'SUCCESS'),
    failure_count = (
        SELECT COUNT(*) FROM plans 
        WHERE goal_id = goal_statistics.goal_id AND status = 'FAILURE');

UPDATE history_statistics
SET success_count = (
        SELECT COUNT(*) FROM plans 
        WHERE history_id = history_statistics.history_id AND status = 'SUCCESS'),
    failure_count = (
        SELECT COUNT(*) FROM plans 
        WHERE history_id = history_statistics.history_id AND status = 'FAILURE');
