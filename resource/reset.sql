USE `car`;

TRUNCATE car.car_order;
TRUNCATE car.`check`;
TRUNCATE car.car_assess;

UPDATE car.car_info
SET status='未出租'
WHERE deleted = 0;