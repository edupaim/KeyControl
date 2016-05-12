-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.27-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para keycontrol
CREATE DATABASE IF NOT EXISTS `keycontrol` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `keycontrol`;


-- Copiando estrutura para tabela keycontrol.chaves
CREATE TABLE IF NOT EXISTS `chaves` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id da chave (int)',
  `cod` varchar(20) NOT NULL COMMENT 'código da chave/sala (string)',
  `sala` varchar(30) NOT NULL COMMENT 'sala da chave (string)',
  `capacidade` int(10) unsigned NOT NULL COMMENT 'capacidade da sala (int)',
  `andar` varchar(30) NOT NULL COMMENT 'andar da sala (string)',
  `tipo` char(1) NOT NULL COMMENT 'tipo da chave (char)',
  `estado` varchar(10) NOT NULL COMMENT 'estado da chave (string)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cod` (`cod`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.chaves: 0 rows
DELETE FROM `chaves`;
/*!40000 ALTER TABLE `chaves` DISABLE KEYS */;
/*!40000 ALTER TABLE `chaves` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL DEFAULT '0',
  `login` varchar(50) NOT NULL DEFAULT '0',
  `senha` varchar(50) NOT NULL DEFAULT '0',
  `tipo` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nome` (`nome`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.usuario: ~0 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome`, `login`, `senha`, `tipo`) VALUES
	(1, 'Administrador', 'admin', 'admin', 0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.usuario_tipo
CREATE TABLE IF NOT EXISTS `usuario_tipo` (
  `usuariotipo_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`usuariotipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.usuario_tipo: ~2 rows (aproximadamente)
DELETE FROM `usuario_tipo`;
/*!40000 ALTER TABLE `usuario_tipo` DISABLE KEYS */;
INSERT INTO `usuario_tipo` (`usuariotipo_id`, `tipo`) VALUES
	(1, 'Administrador'),
	(2, 'Funcionario');
/*!40000 ALTER TABLE `usuario_tipo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
