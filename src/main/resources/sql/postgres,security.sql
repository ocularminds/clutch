CREATE POLICY role_policy ON CarSpecifications
USING (role = current_setting('app.current_user_role'));

ALTER TABLE car_specs ENABLE ROW LEVEL SECURITY;

SET app.current_user_role = 'dev';
SELECT * FROM car_specs;
