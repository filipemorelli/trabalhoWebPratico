-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.13-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para javai
CREATE DATABASE IF NOT EXISTS `javai` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `javai`;


-- Copiando estrutura para tabela javai.endereco
CREATE TABLE IF NOT EXISTS `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `endereco` varchar(255) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela javai.endereco: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;


-- Copiando estrutura para tabela javai.oferecercarona
CREATE TABLE IF NOT EXISTS `oferecercarona` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `considerecoes` text,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endereco_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6fwn99dkqd8gvqid1gealwyu4` (`endereco_id`),
  KEY `FK_i111ofvrp99lckw5giygosrpb` (`user_id`),
  CONSTRAINT `FK_6fwn99dkqd8gvqid1gealwyu4` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  CONSTRAINT `FK_i111ofvrp99lckw5giygosrpb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela javai.oferecercarona: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `oferecercarona` DISABLE KEYS */;
/*!40000 ALTER TABLE `oferecercarona` ENABLE KEYS */;


-- Copiando estrutura para tabela javai.pedircarona
CREATE TABLE IF NOT EXISTS `pedircarona` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `considerecoes` text,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endereco_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n95jsdgrnjwhsa19r7hi39k6j` (`endereco_id`),
  KEY `FK_mq59inqwd5tmlou0rykx8f4fn` (`user_id`),
  CONSTRAINT `FK_mq59inqwd5tmlou0rykx8f4fn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_n95jsdgrnjwhsa19r7hi39k6j` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela javai.pedircarona: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pedircarona` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedircarona` ENABLE KEYS */;


-- Copiando estrutura para tabela javai.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `id_social` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nome` varchar(255) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `url_imagem` text,
  `url_social` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
