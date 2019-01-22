drop  database if exists gamequiz ; 
create database gamequiz;
use gamequiz;


CREATE TABLE `question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(500),
  `theme` enum('history', 'sport', 'geographiy', 'biologiy', 'medicine', 'literature', 'art', 'science'),
  `level` enum('1', '2', '3'),
  `used` bool ,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

INSERT INTO `question` VALUES 
(1, 'easy1', 1, 1, false),
(2, 'easy2', 3, 1, false),
(3, 'easy3', 3, 1, false),
(4, 'easy4', 3, 1, false),
(5, 'easy5', 3, 1, false),
(6, 'easy6', 3, 1, false),
(7, 'easy7', 3, 1, false),
(8, 'medium1', 3, 2, false),
(9, 'medium2', 3, 2, false),
(10, 'medium3', 3, 2, false),
(11, 'medium4', 3, 2, false),
(12, 'medium5', 3, 2, false),
(13, 'medium6', 3, 2, false),
(14, 'hard1', 3, 3, false),
(15, 'hard2', 3, 3, false),
(16, 'hard3', 3, 3, false),
(17, 'hard4', 3, 3, false),
(18, 'hard5', 3, 3, false),
(19, 'hard6', 3, 3, false),
(20, 'hard7', 3, 3, false);


	

CREATE TABLE `answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer` VARCHAR(500),
  `id_question` INT,

  PRIMARY KEY (`id`),
  KEY `FK1` (`id_question`),
  CONSTRAINT `FK1` 
  FOREIGN KEY (`id_question`) 
  REFERENCES `question` (`id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

INSERT INTO `answer` VALUES 
(1, '135 метров', 1),
(2, '168 метров', 1),
(3, '110 метров', 1),

(4, '19', 2),
(5, '20', 2),
(6, '18', 2),

(10, '19', 4),
(11, '20', 4),
(12, '18', 4),

(7, 'около 4000 метров', 3),
(8, 'около 2000 метров', 3),
(9, 'около 5000 метров', 3);

CREATE TABLE `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10),
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;


INSERT INTO `role` VALUES(1, 'ROLE_USER');
INSERT INTO `role` VALUES(2, 'ROLE_ADMIN');

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),  
  `username` VARCHAR(40), 
  `email` VARCHAR(50), 
  `password` VARCHAR(100), 
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

INSERT INTO `user` VALUES(1, 'admin', 'admin', 'admin@admin', '$2a$10$O9Hf5m.TmUNMoBehstMy9uuBuhwgI1GYMEYobbiZvDcm660hsaHT.');
INSERT INTO `user` VALUES(2, 'user', 'user', 'user@user', '$2a$10$bLJKzwffRvPkk5p2DFLV6eu/pjFSpa9mh.OxVDNNLbENgdWoYM3ca');

CREATE TABLE `user_roles` (  
  `id_user` INT NOT NULL,
  `id_role` INT NOT NULL,
  
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

INSERT INTO `user_roles` VALUES (2, 1);
INSERT INTO `user_roles` VALUES (1, 2);

