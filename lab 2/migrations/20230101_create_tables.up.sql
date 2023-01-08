CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `movie_id` int NOT NULL,
  `movie_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_movie_id` (`movie_id`),
  CONSTRAINT `FK_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
);
INSERT INTO `cinema`.`movies` (`id`, `name`) VALUES ('1', 'Моя незграбна фея');
INSERT INTO `cinema`.`movies` (`id`, `name`) VALUES ('2', 'Хижак');
INSERT INTO `cinema`.`movies` (`id`, `name`) VALUES ('3', 'Дививижний Моріс');
GRANT ALL ON cinema.* to 'moviesuser'@'%';