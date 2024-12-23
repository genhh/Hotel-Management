
DROP DATABASE IF EXISTS hotel_manage;
CREATE DATABASE hotel_manage;
USE hotel_manage;

-- INSERT INTO `booking` VALUES ('1', '1', '100', '2022-08-07 22:48:29', '2022-08-14 13:49:05');
-- status 和 payment_status 字段被假设为整数类型, 1 表示已支付或未预约，0 表示未支付或已预约
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `about` text DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hotel` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `about` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wallet` (
  `wallet_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `balance` double(9,2) DEFAULT NULL,

  PRIMARY KEY (`wallet_id`),
  KEY `FK_user` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transactions` (
  `transaction_id` varchar(255) NOT NULL,
  `wallet_id` varchar(255) DEFAULT NULL,
  `transaction_date` DATE DEFAULT NULL,
  `amount` double(9,2) DEFAULT NULL,
  `current_balance` double(9,2) DEFAULT NULL,
  `type` int DEFAULT NULL,

  PRIMARY KEY(`transaction_id`),
  KEY `FK_wallet` (`wallet_id`),
  FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rating` (
  `rating_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `hotel_id` varchar(255) NOT NULL,
  `rating` int DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rating_id`),
  KEY `FK_user` (`user_id`),
  KEY `FK_hotel` (`hotel_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `room` (
  `room_id` varchar(255) NOT NULL,
  `hotel_id` varchar(255) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `room_number` varchar(255) DEFAULT NULL,
  `room_price` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `room_description` text DEFAULT NULL,

  PRIMARY KEY (`room_id`),
  KEY `FK_hotel` (`hotel_id`),
  FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `booking` (
  `booking_id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `hotel_id` varchar(255) DEFAULT NULL,

  `booking_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `payment_status` int DEFAULT NULL,
  `booking_status` int DEFAULT NULL,

  PRIMARY KEY (`booking_id`),
  KEY `FK_user` (`user_id`),
  KEY `FK_room` (`room_id`),
  KEY `FK_hotel_booking` (`hotel_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `hotel` (`id`, `name`, `location`, `about`) VALUES
('h1', 'Grand Hotel', 'New York', 'A luxurious hotel in the heart of New York.'),
('h2', 'Seaside Resort', 'Florida', 'A beautiful resort by the sea.'),
('h3', 'Mountain Lodge', 'Colorado', 'A cozy lodge with mountain views.'),
('h4', 'Urban Inn', 'Chicago', 'A modern inn in downtown Chicago.'),
('h5', 'Desert Retreat', 'Nevada', 'An oasis in the desert.');

INSERT INTO `user` (`user_id`, `name`, `email`, `about`) VALUES
('u1', 'John Doe', 'john.doe@example.com', 'Traveler and explorer.'),
('u2', 'Jane Smith', 'jane.smith@example.com', 'Loves the beach and sun.'),
('u3', 'Alice Johnson', 'alice.johnson@example.com', 'Adventure seeker and nature lover.'),
('u4', 'Mike Brown', 'mike.brown@example.com', 'City dweller and business traveler.'),
('u5', 'Emma Davis', 'emma.davis@example.com', 'Enjoys the tranquility of the mountains.');

INSERT INTO `wallet` (`wallet_id`, `user_id`, `balance`) VALUES
('w1', 'u1', 7.0),
('w2', 'u2', 56.0),
('w3', 'u3', 15.2),
('w4', 'u4', 110.2),
('w5', 'u5', 6523.0);

INSERT INTO `transactions` (`transaction_id`, `wallet_id`, `transaction_date`, `amount`,`current_balance`,`type`) VALUES
('t1', 'w1', '2024-01-10', 15.01, 100.00, 0),
('t2', 'w2', '2024-02-15', 52.01, 100.00, 1),
('t3', 'w3', '2024-03-01', 35.01, 100.00, 0),
('t4', 'w4', '2024-04-12', 45.01, 100.00, 1),
('t5', 'w5', '2024-05-20', 50.01, 100.00, 0);

INSERT INTO `rating` (`rating_id`, `user_id`, `hotel_id`, `rating`, `remark`) VALUES
('r1', 'u1', 'h1', '15', 'not good23'),
('r2', 'u2', 'h2', '52', 'not good wef'),
('r3', 'u3', 'h1', '35', 'not good sda'),
('r4', 'u4', 'h2', '45', 'b good'),
('r5', 'u5', 'h1', '50', 'c good');

INSERT INTO `room` (`room_id`, `hotel_id`, `room_type`, `room_number`, `room_price`, `status`, `room_description`) VALUES
('101', 'h1', 'Single', '101', 100, 1, 'Comfortable single room with a view.'),
('102', 'h1', 'Double', '102', 150, 1, 'Spacious double room with city view.'),
('103', 'h1', 'Suite', '103', 250, 0, 'Luxury suite with a living area.'),
('201', 'h2', 'Beachfront', '201', 200, 1, 'Room with direct beach access.'),
('202', 'h2', 'Poolside', '202', 180, 1, 'Room near the pool area.');

INSERT INTO `booking` (`booking_id`, `user_id`, `room_id`, `hotel_id`, `booking_date`, `check_out_date`, `amount`, `payment_status`,`booking_status` ) VALUES
('b1', 'u1', '101', 'h1', '2024-01-10', '2024-01-15', 500, 1,0),
('b2', 'u2', '201', 'h2', '2024-02-15', '2024-02-20', 800, 1,1),
('b3', 'u3', '103', 'h1', '2024-03-01', '2024-03-10', 1000, 0,0),
('b4', 'u4', '202', 'h2', '2024-04-12', '2024-04-17', 650, 1,1),
('b5', 'u5', '101', 'h1', '2024-05-20', '2024-05-25', 450, 0,0);

