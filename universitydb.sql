-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Май 01 2019 г., 14:06
-- Версия сервера: 10.1.13-MariaDB
-- Версия PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `universitydb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `appointments`
--

CREATE TABLE `appointments` (
  `employerId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `appointments`
--

INSERT INTO `appointments` (`employerId`, `departmentId`) VALUES
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(4, 2),
(5, 2),
(6, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `degreenames`
--

CREATE TABLE `degreenames` (
  `id` int(11) NOT NULL,
  `name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `degreenames`
--

INSERT INTO `degreenames` (`id`, `name`) VALUES
(1, 'assistant'),
(2, 'associate professor'),
(3, 'professor');

-- --------------------------------------------------------

--
-- Структура таблицы `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `headId` int(11) NOT NULL,
  `departmentName` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `departments`
--

INSERT INTO `departments` (`id`, `headId`, `departmentName`) VALUES
(1, 3, 'Computer sience'),
(2, 6, 'Economic');

-- --------------------------------------------------------

--
-- Структура таблицы `employers`
--

CREATE TABLE `employers` (
  `id` int(11) NOT NULL,
  `degreeId` int(11) NOT NULL,
  `fullName` tinytext NOT NULL,
  `salary` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `employers`
--

INSERT INTO `employers` (`id`, `degreeId`, `fullName`, `salary`) VALUES
(1, 1, 'Elissa Bone', 300),
(2, 2, 'Katerina Hays', 330),
(3, 3, 'Lyle Hamer', 400),
(4, 1, 'Aarron Burrows', 250),
(5, 2, 'Nico Whyte', 340),
(6, 3, 'Lilia O''Gallagher', 450);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`departmentId`,`employerId`);

--
-- Индексы таблицы `degreenames`
--
ALTER TABLE `degreenames`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `employers`
--
ALTER TABLE `employers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `degreenames`
--
ALTER TABLE `degreenames`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `employers`
--
ALTER TABLE `employers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
