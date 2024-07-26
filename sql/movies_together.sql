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
  user_email varchar(200) unique NOT NULL
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


drop table if exists watchmode_tmdb;

CREATE table watchmode_tmdb(

	watchmode_id int not null,
    tmdb_id int not null,
    title varchar(255),
    
    primary key(tmdb_id)

);


drop table if exists movies;

create table movies(
	
    id int primary key auto_increment,
    tmdb_id int,
    watchmode_id int not null,
    title varchar(255),
    director varchar(255),
    year int,
    description varchar(1000),
    poster_path varchar(400)

);

drop table if exists streaming_source;

create table streaming_source(

	source_id int primary key auto_increment,
    name varchar(255),
    logo varchar(400)

);
drop table if exists format;

create table format(
	
    format_id int primary key auto_increment,
    name varchar(10)
);

drop table if exists movie_source;

create table movie_source(

	id int primary key auto_increment,
    source_id int,
    movie_id int,
    type varchar(100),
    region varchar(5),
    web_url varchar(500),
    format_id int,
    price double,
    seasons int,
    episodes int,
    
    foreign key(source_id) references streaming_source(source_id),
    foreign key (movie_id) references movies(id),
    foreign key (format_id) references `format`(format_id),
    
    KEY `FK_MOVIE_idx` (movie_id)
);


drop table if exists movie_event;

create table movie_event(

	id int primary key auto_increment,
    owner_id int,
    title varchar(250),
    event_datetime datetime not null,
    movie_id int,
    location varchar(255),
    description varchar(500),
    
    foreign key(movie_id) references movies(id),
    foreign key(owner_id) references user(user_id)

);


drop table if exists user_status;

create table user_status(

	id int auto_increment primary key,
    name varchar(255)

);

insert into user_status(name) values("invited"),("accepted"), ("declined");


drop table if exists user_event;

create table user_event(

	id int primary key auto_increment,
    event_id int,
    user_id int,
    status_id int,
    
    foreign key(status_id) references user_status(id),
    foreign key(event_id) references movie_event(id),
    foreign key(user_id) references user(user_id)

);


drop table if exists watchLists;

create table watchLists(
	
    list_id int primary key auto_increment,
    user_id int,
    name varchar(255),
    
    foreign key(user_id) references user(user_id)

);

drop table if exists list_item;

create table list_item(

	item_id int primary key auto_increment,
    list_id int,
    movie_id int,
    list_rank int,
    
    foreign key(list_id) references watchLists(list_id),
    foreign key(movie_id) references movies(id)

);




