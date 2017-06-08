--#drop database wwjd;
--#create database wwjd;
--#use wwjd;
--#source /Users/MattyG/IdeaProjects/wwjd/sql/create_tables.sql;

CREATE TABLE `users` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
);

INSERT INTO `users` VALUES ('matt','matt',1);


CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`,`authority`),
  KEY `FK_auth_username` (`username`),
      CONSTRAINT `FK_auth_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`));

INSERT INTO `authorities` VALUES ('matt','ROLE_ADMIN'),('matt','ROLE_USER');

CREATE TABLE `song` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `keyy` varchar(256) NOT NULL,
  `genre` varchar(256) NOT NULL,
  `chords` varchar(256) NOT NULL,
  `username` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_song_username` (`username`),
    CONSTRAINT `FK_song_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`));



