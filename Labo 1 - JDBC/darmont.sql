/*
 Navicat Premium Data Transfer

 Source Server         : Connexion 1
 Source Server Type    : MySQL
 Source Server Version : 50140
 Source Host           : localhost
 Source Database       : darmont1

 Target Server Type    : MySQL
 Target Server Version : 50140
 File Encoding         : utf-8

 Date: 10/06/2011 15:04:17 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
--  Table structure for `etudiant`
-- ----------------------------
DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE `etudiant` (
  `NUMETU` int(11) NOT NULL,
  `NOM` varchar(30) COLLATE utf8_bin NOT NULL,
  `PRENOM` varchar(30) COLLATE utf8_bin NOT NULL,
  `DATENAISS` date NOT NULL,
  `RUE` varchar(30) COLLATE utf8_bin NOT NULL,
  `CP` varchar(30) COLLATE utf8_bin NOT NULL,
  `VILLE` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NUMETU`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `etudiant`
-- ----------------------------
BEGIN;
INSERT INTO `etudiant` VALUES ('110', 'Dupont', 'Albert', '1980-06-01', 'Rue de Crimée', '69001', 'Lyon'), ('222', 'West', 'James', '1983-09-03', 'Studio', '', 'Hollywood'), ('300', 'Martin', 'Marie', '1988-06-05', 'Rue des Acacias', '69130', 'Ecully'), ('421', 'Durand', 'Gaston', '1980-11-15', 'Rue de la Meuse', '69008', 'Lyon'), ('575', 'Titgoutte', 'Justine', '1985-02-28', 'Chemin du Château', '69630', 'Chaponost'), ('667', 'Dupond', 'Noémie', '1987-09-18', 'Rue de Dôle', '69007', 'Lyon'), ('999', 'Phantom', 'Marcel', '1960-01-30', '', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `matiere`
-- ----------------------------
DROP TABLE IF EXISTS `matiere`;
CREATE TABLE `matiere` (
  `CODEMAT` varchar(10) COLLATE utf8_bin NOT NULL,
  `LIBELLE` varchar(30) COLLATE utf8_bin NOT NULL,
  `COEF` float NOT NULL,
  PRIMARY KEY (`CODEMAT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `matiere`
-- ----------------------------
BEGIN;
INSERT INTO `matiere` VALUES ('STA', 'Statistique', '0.4'), ('INF', 'Informatique', '0.4'), ('ECO', 'Econométrie', '0.2');
COMMIT;


-- ----------------------------
--  Table structure for `epreuve`
-- ----------------------------
DROP TABLE IF EXISTS `epreuve`;
CREATE TABLE `epreuve` (
  `NUMEPREUVE` int(11) NOT NULL,
  `DATEPREUVE` date NOT NULL,
  `LIEU` varchar(30) COLLATE utf8_bin NOT NULL,
  `CODEMAT` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `epreuve`
-- ----------------------------
BEGIN;
INSERT INTO `epreuve` VALUES ('11031', '2003-12-15', 'Salle 191L', 'STA'), ('11032', '2004-04-01', 'Amphi G', 'STA'), ('21031', '2003-10-30', 'Salle 191L', 'INF'), ('21032', '2004-06-01', 'Salle 192L', 'INF'), ('31030', '2004-06-02', 'Salle 05R', 'ECO');
COMMIT;

-- ----------------------------
--  Table structure for `notation`
-- ----------------------------
DROP TABLE IF EXISTS `notation`;
CREATE TABLE `notation` (
  `NUMETU` int(11) NOT NULL,
  `NUMEPREUVE` int(11) NOT NULL,
  `NOTE` float NOT NULL,
  PRIMARY KEY (`NUMETU`,`NUMEPREUVE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `notation`
-- ----------------------------
BEGIN;
INSERT INTO `notation` VALUES ('110', '11031', '10'), ('110', '11032', '11.5'), ('110', '21031', '8.5'), ('110', '21032', '0'), ('110', '31030', '13'), ('222', '11031', '9'), ('222', '11032', '14'), ('222', '21031', '12'), ('222', '21032', '16'), ('222', '31030', '20'), ('300', '11031', '14'), ('300', '11032', '20'), ('300', '21031', '20'), ('300', '21032', '13.5'), ('421', '11031', '5.5'), ('421', '11032', '7'), ('421', '21031', '1.5'), ('421', '21032', '0'), ('421', '31030', '10'), ('575', '11031', '13'), ('575', '11032', '9'), ('575', '21031', '12.5'), ('575', '21032', '14'), ('575', '31030', '7'), ('667', '11031', '16'), ('667', '11032', '20'), ('667', '21031', '8.5'), ('667', '21032', '9.5');
COMMIT;

