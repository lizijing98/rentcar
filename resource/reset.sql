USE `car`;

TRUNCATE car.car_order;
TRUNCATE car.`check`;
TRUNCATE car.car_assess;
TRUNCATE car.login_log;

UPDATE car.car_info
SET status='未出租'
WHERE deleted = 0;