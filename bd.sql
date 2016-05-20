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


-- Copiando estrutura para tabela keycontrol.beneficiario
CREATE TABLE IF NOT EXISTS `beneficiario` (
  `id_beneficiario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `matricula` varchar(60) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`id_beneficiario`),
  UNIQUE KEY `nome` (`nome`),
  UNIQUE KEY `matricula` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.beneficiario: ~2 rows (aproximadamente)
DELETE FROM `beneficiario`;
/*!40000 ALTER TABLE `beneficiario` DISABLE KEYS */;
INSERT INTO `beneficiario` (`id_beneficiario`, `nome`, `matricula`, `tipo`) VALUES
	(1, 'Eduardo', '2121', 0),
	(2, 'Felipe', '2222', 0);
/*!40000 ALTER TABLE `beneficiario` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.beneficiario_tipo
CREATE TABLE IF NOT EXISTS `beneficiario_tipo` (
  `beneficiariotipo_id` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.beneficiario_tipo: ~2 rows (aproximadamente)
DELETE FROM `beneficiario_tipo`;
/*!40000 ALTER TABLE `beneficiario_tipo` DISABLE KEYS */;
INSERT INTO `beneficiario_tipo` (`beneficiariotipo_id`, `tipo`) VALUES
	(0, 'Aluno'),
	(1, 'Professor');
/*!40000 ALTER TABLE `beneficiario_tipo` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.chave
CREATE TABLE IF NOT EXISTS `chave` (
  `id_chave` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id da chave (int)',
  `sala` varchar(30) NOT NULL COMMENT 'sala da chave (string)',
  `capacidade` int(10) NOT NULL COMMENT 'capacidade da sala (int)',
  `tipo` int(11) NOT NULL COMMENT 'tipo da chave (char)',
  `id_beneficiario` int(10) NOT NULL DEFAULT '0' COMMENT 'estado da chave (string)',
  PRIMARY KEY (`id_chave`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.chave: 7 rows
DELETE FROM `chave`;
/*!40000 ALTER TABLE `chave` DISABLE KEYS */;
INSERT INTO `chave` (`id_chave`, `sala`, `capacidade`, `tipo`, `id_beneficiario`) VALUES
	(2, '101', 50, 0, 0),
	(3, '102', 50, 0, 0),
	(4, '201', 20, 0, 0),
	(5, '12', 21, 1, 0),
	(6, '14', 21, 1, 0),
	(7, '25', 21, 1, 0),
	(8, '1', 10, 2, 0);
/*!40000 ALTER TABLE `chave` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.chave_tipo
CREATE TABLE IF NOT EXISTS `chave_tipo` (
  `id_tipo_chave` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.chave_tipo: ~2 rows (aproximadamente)
DELETE FROM `chave_tipo`;
/*!40000 ALTER TABLE `chave_tipo` DISABLE KEYS */;
INSERT INTO `chave_tipo` (`id_tipo_chave`, `tipo`) VALUES
	(0, 'Sala'),
	(1, 'Laboratorio'),
	(2, 'Reuniao');
/*!40000 ALTER TABLE `chave_tipo` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.historico
CREATE TABLE IF NOT EXISTS `historico` (
  `id_historico` int(11) NOT NULL AUTO_INCREMENT,
  `id_beneficiario` int(11) NOT NULL,
  `data_operacao` datetime NOT NULL,
  `id_chave` int(11) NOT NULL,
  `tipo_operacao` int(11) NOT NULL,
  PRIMARY KEY (`id_historico`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.historico: ~0 rows (aproximadamente)
DELETE FROM `historico`;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` (`id_historico`, `id_beneficiario`, `data_operacao`, `id_chave`, `tipo_operacao`) VALUES
	(1, 1, '2016-05-20 18:29:26', 2, 0),
	(2, 1, '2016-05-20 18:29:28', 7, 0),
	(3, 1, '2016-05-20 18:29:29', 8, 0),
	(4, 1, '2016-05-20 18:32:17', 2, 1),
	(5, 1, '2016-05-20 18:32:20', 7, 1),
	(6, 1, '2016-05-20 18:32:22', 8, 1);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.historico_tipo
CREATE TABLE IF NOT EXISTS `historico_tipo` (
  `id_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.historico_tipo: ~2 rows (aproximadamente)
DELETE FROM `historico_tipo`;
/*!40000 ALTER TABLE `historico_tipo` DISABLE KEYS */;
INSERT INTO `historico_tipo` (`id_tipo`, `tipo`) VALUES
	(0, 'Emprestimo'),
	(1, 'Devolucao');
/*!40000 ALTER TABLE `historico_tipo` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.horario
CREATE TABLE IF NOT EXISTS `horario` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `comeco` time NOT NULL,
  `fim` time NOT NULL,
  PRIMARY KEY (`id_horario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.horario: ~8 rows (aproximadamente)
DELETE FROM `horario`;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` (`id_horario`, `comeco`, `fim`) VALUES
	(1, '07:00:00', '08:50:00'),
	(2, '08:50:00', '10:40:00'),
	(3, '10:40:00', '12:30:00'),
	(4, '13:00:00', '14:50:00'),
	(5, '14:50:00', '16:40:00'),
	(6, '16:40:00', '18:30:00'),
	(7, '18:30:00', '20:20:00'),
	(8, '20:20:00', '22:10:00');
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.reserva
CREATE TABLE IF NOT EXISTS `reserva` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `id_chave` int(11) NOT NULL,
  `id_beneficiario` int(11) NOT NULL,
  `data_entrada` date NOT NULL,
  `data_saida` date NOT NULL,
  `id_horario` int(11) NOT NULL,
  PRIMARY KEY (`id_reserva`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.reserva: ~3 rows (aproximadamente)
DELETE FROM `reserva`;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` (`id_reserva`, `id_chave`, `id_beneficiario`, `data_entrada`, `data_saida`, `id_horario`) VALUES
	(1, 2, 2, '2222-01-21', '2222-01-22', 0),
	(4, 4, 2, '2016-01-20', '2016-01-28', 0),
	(8, 4, 1, '2016-01-28', '2016-01-29', 0);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.usuario: ~2 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome`, `login`, `senha`, `tipo`) VALUES
	(1, 'Administrador', 'admin', 'admin', 0),
	(2, 'Eduardo', 'edu', 'edu', 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Copiando estrutura para tabela keycontrol.usuario_tipo
CREATE TABLE IF NOT EXISTS `usuario_tipo` (
  `usuariotipo_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`usuariotipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela keycontrol.usuario_tipo: ~2 rows (aproximadamente)
DELETE FROM `usuario_tipo`;
/*!40000 ALTER TABLE `usuario_tipo` DISABLE KEYS */;
INSERT INTO `usuario_tipo` (`usuariotipo_id`, `tipo`) VALUES
	(0, 'Administrador'),
	(1, 'Funcionario');
/*!40000 ALTER TABLE `usuario_tipo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
