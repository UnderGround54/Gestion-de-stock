
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


DROP TABLE IF EXISTS `bonentree`;
CREATE TABLE IF NOT EXISTS `bonentree` (
  `numBe` char(10) NOT NULL,
  `dateEntree` char(10) DEFAULT NULL,
  PRIMARY KEY (`numBe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `bonentree` (`numBe`, `dateEntree`) VALUES
('BE_02', '2019-09-18'),
('BE_03', '2018-09-17'),
('BE_04', '2019-09-17'),
('BE_05', '2017-09-08'),
('BE_06', '2019-09-13');

DROP TABLE IF EXISTS `bonsortie`;
CREATE TABLE IF NOT EXISTS `bonsortie` (
  `numBs` char(10) NOT NULL,
  `dateSortie` char(10) DEFAULT NULL,
  PRIMARY KEY (`numBs`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `bonsortie` (`numBs`, `dateSortie`) VALUES
('BS_01', '2019-09-13'),
('BS_02', '2019-10-24'),
('BS_03', '2019-09-18'),
('BS_05', '2019-09-18');


DROP TABLE IF EXISTS `lignebe`;
CREATE TABLE IF NOT EXISTS `lignebe` (
  `idLigneBe` int(4) NOT NULL AUTO_INCREMENT,
  `numBe` char(10) NOT NULL,
  `numProduit` char(10) NOT NULL,
  `qteEntree` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLigneBe`),
  KEY `FK_BE` (`numBe`),
  KEY `FK_BE_2` (`numProduit`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


INSERT INTO `lignebe` (`idLigneBe`, `numBe`, `numProduit`, `qteEntree`) VALUES
(2, 'BE_05', 'PRO_02', 4),
(4, 'BE_03', 'PRO_03', 5),
(7, 'BE_02', 'PRO_06', 5);


DROP TABLE IF EXISTS `lignebs`;
CREATE TABLE IF NOT EXISTS `lignebs` (
  `idLigneBs` int(4) NOT NULL AUTO_INCREMENT,
  `numBs` char(10) NOT NULL,
  `numProduit` char(10) NOT NULL,
  `qteSortie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLigneBs`),
  KEY `FK_BS` (`numBs`),
  KEY `FK_BS_2` (`numProduit`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


INSERT INTO `lignebs` (`idLigneBs`, `numBs`, `numProduit`, `qteSortie`) VALUES
(4, 'BS_01', 'PRO_05', 20),
(5, 'BS_05', 'PRO_06', 4);

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `numProduit` char(10) NOT NULL,
  `design` char(60) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`numProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `produit` (`numProduit`, `design`, `stock`) VALUES
('PRO_02', 'Katsaka', 34),
('PRO_03', 'Tsaramaso', 30),
('PRO_04', 'Vonemba', 12),
('PRO_05', 'Akondro', 33),
('PRO_06', 'Lait', 11);

ALTER TABLE `lignebe`
  ADD CONSTRAINT `FK_BE` FOREIGN KEY (`numBe`) REFERENCES `bonentree` (`numBe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_BE_2` FOREIGN KEY (`numProduit`) REFERENCES `produit` (`numProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `lignebs`
  ADD CONSTRAINT `FK_BS` FOREIGN KEY (`numBs`) REFERENCES `bonsortie` (`numBs`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_BS_2` FOREIGN KEY (`numProduit`) REFERENCES `produit` (`numProduit`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
