-- phpMyAdmin SQL Dump
-- version 4.3.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 13/05/2016 às 10:12
-- Versão do servidor: 5.5.41
-- Versão do PHP: 5.4.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `admin_key`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `beneficiario`
--

CREATE TABLE IF NOT EXISTS `beneficiario` (
  `id_beneficiario` int(11) NOT NULL,
  `nome` varchar(512) NOT NULL,
  `matricula` varchar(60) NOT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `beneficiario_tipo`
--

CREATE TABLE IF NOT EXISTS `beneficiario_tipo` (
  `beneficiariotipo_id` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `beneficiario_tipo`
--

INSERT INTO `beneficiario_tipo` (`beneficiariotipo_id`, `tipo`) VALUES
(1, 'Aluno'),
(2, 'Professor');

-- --------------------------------------------------------

--
-- Estrutura para tabela `chave`
--

CREATE TABLE IF NOT EXISTS `chave` (
  `id_chave` int(10) unsigned NOT NULL COMMENT 'id da chave (int)',
  `sala` varchar(30) NOT NULL COMMENT 'sala da chave (string)',
  `capacidade` int(10) unsigned NOT NULL COMMENT 'capacidade da sala (int)',
  `tipo` int(11) NOT NULL COMMENT 'tipo da chave (char)',
  `id_beneficiario` int(10) DEFAULT NULL COMMENT 'estado da chave (string)'
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `chave`
--

INSERT INTO `chave` (`id_chave`, `sala`, `capacidade`, `tipo`, `id_beneficiario`) VALUES
(2, '101', 50, 0, NULL),
(3, '101', 50, 0, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `chave_tipo`
--

CREATE TABLE IF NOT EXISTS `chave_tipo` (
  `id_tipo_chave` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `chave_tipo`
--

INSERT INTO `chave_tipo` (`id_tipo_chave`, `tipo`) VALUES
(0, 'Sala'),
(1, 'Laboratorio'),
(2, 'Reuniao');

-- --------------------------------------------------------

--
-- Estrutura para tabela `historico`
--

CREATE TABLE IF NOT EXISTS `historico` (
  `id_historico` int(11) NOT NULL,
  `id_beneficiario` int(11) NOT NULL,
  `data_operacao` date NOT NULL,
  `id_chave` int(11) NOT NULL,
  `tipo_operacao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL DEFAULT '0',
  `login` varchar(50) NOT NULL DEFAULT '0',
  `senha` varchar(50) NOT NULL DEFAULT '0',
  `tipo` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nome`, `login`, `senha`, `tipo`) VALUES
(1, 'Administrador', 'admin', 'admin', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_tipo`
--

CREATE TABLE IF NOT EXISTS `usuario_tipo` (
  `usuariotipo_id` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `usuario_tipo`
--

INSERT INTO `usuario_tipo` (`usuariotipo_id`, `tipo`) VALUES
(1, 'Funcionario'),
(3, 'Administrador');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `beneficiario`
--
ALTER TABLE `beneficiario`
  ADD PRIMARY KEY (`id_beneficiario`);

--
-- Índices de tabela `beneficiario_tipo`
--
ALTER TABLE `beneficiario_tipo`
  ADD PRIMARY KEY (`beneficiariotipo_id`);

--
-- Índices de tabela `chave`
--
ALTER TABLE `chave`
  ADD PRIMARY KEY (`id_chave`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`), ADD UNIQUE KEY `nome` (`nome`), ADD UNIQUE KEY `login` (`login`);

--
-- Índices de tabela `usuario_tipo`
--
ALTER TABLE `usuario_tipo`
  ADD PRIMARY KEY (`usuariotipo_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `beneficiario_tipo`
--
ALTER TABLE `beneficiario_tipo`
  MODIFY `beneficiariotipo_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `chave`
--
ALTER TABLE `chave`
  MODIFY `id_chave` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id da chave (int)',AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `usuario_tipo`
--
ALTER TABLE `usuario_tipo`
  MODIFY `usuariotipo_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
