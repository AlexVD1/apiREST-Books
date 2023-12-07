USE `springproject`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

-- *************************
-- Estructura de tabla `users`
-- *************************

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ***************************
-- insertando datos en `users`
-- ***************************

INSERT INTO `users` 
VALUES 
('alexbeat','{bcrypt}$2y$10$6Wh.y5r4ObFGaPD98n.g5.X9ynPmjujalCNwN/i9xSDNgvBtj1Qbe',1), -- @alexbeat#Pass01
('user01','{bcrypt}$2y$10$2GxLSbkaw4nM2BIKu80WX.mc9rBftVk.LHhncGLP.kW5Nk3gNjzji',1), -- @user01#Pass02
('user02','{bcrypt}$2y$10$UWi8IT1xFLfc5/9.EuPrHO5jyi/QgcFIIWcbcy0tfwgJtVw/6G8wC',1); -- @user02#Pass03


-- *********************************
-- Estructura de tabla `authorities`
-- *********************************

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ***********************************
-- Insertando datos en `authorities`
-- **********************************

INSERT INTO `authorities` 
VALUES 
('alexbeat','ROLE_Gamma'),
('user01','ROLE_Gamma'),
('user01','ROLE_Admin'),
('user02','ROLE_Gamma'),
('alexbeat','ROLE_Admin')


