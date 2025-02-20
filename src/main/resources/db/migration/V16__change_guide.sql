DELETE FROM user_guides;

ALTER TABLE user_guides
DROP CONSTRAINT guide_type_check;

ALTER TABLE user_guides
ADD CONSTRAINT guide_type_check CHECK (type IN ('NEW_PLAN', 'NEW_GOAL', 'UPDATE_PLAN', 'USER_TYPE'));

