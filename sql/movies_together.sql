drop database if exists MoviesTogether;
CREATE DATABASE  IF NOT EXISTS `MoviesTogether`;
USE `MoviesTogether`;



CREATE TABLE user (
  `user_id` int PRIMARY KEY auto_increment,
  `first_name` varchar(255),
  `last_name` varchar(255),
  user_name varchar(255) NOT NULL,
  password char(80),
  enabled tinyint,
  user_email varchar(100) unique NOT NULL
);


DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`user_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
);


INSERT INTO user(first_name, last_name, user_name, user_email, password, enabled)
    VALUES ('Luke', 'Hanrahan', 'lah', 'lah5544@psu.edu', '$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha',1 );

INSERT INTO user(first_name, last_name, user_name, user_email, password, enabled)
    VALUES ('John', 'Doe','jod', 'jod1234@psu.edu' , '$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha' ,1 );

INSERT INTO user(first_name, last_name, user_name, user_email, password, enabled)
    VALUES ('Jane', 'Doe','jad', 'jad4231@psu.edu' , '$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha' ,1 );
    
INSERT INTO user(first_name, last_name, user_name, user_email, password, enabled)
    VALUES ('Anonymous', 'User','anon', 'anonymousUser' , '$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha' ,1 );
 
INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN'), ('ROLE_GUEST'); 
INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(4,3);