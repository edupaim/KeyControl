-- phpMyAdmin SQL Dump
-- version 4.3.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 13/05/2016 às 00:17
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

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `beneficiario_tipo`
--
ALTER TABLE `beneficiario_tipo`
  ADD PRIMARY KEY (`beneficiariotipo_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `beneficiario_tipo`
--
ALTER TABLE `beneficiario_tipo`
  MODIFY `beneficiariotipo_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
