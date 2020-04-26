-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:33065
-- Tiempo de generación: 25-04-2020 a las 19:39:05
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `shadiagram`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amistad`
--

CREATE TABLE `amistad` (
  `usuario` int(11) DEFAULT NULL,
  `amistad` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `fechasolicitud` date DEFAULT NULL,
  `estado` int(11) DEFAULT NULL COMMENT 'Almacena el estado de la solicitud, 0 si fue realizada, 1 si se aprobo y 2 si fue rechazada'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena la relación de amistad entre los usuarios';

--
-- Volcado de datos para la tabla `amistad`
--

INSERT INTO `amistad` (`usuario`, `amistad`, `id`, `fechasolicitud`, `estado`) VALUES
(5, 3, 1, NULL, NULL),
(5, 2, 2, NULL, NULL),
(5, 1, 3, NULL, NULL),
(5, 3, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `publicacion` int(11) DEFAULT NULL COMMENT 'Almacena el identificador de la publicacipon del usuario',
  `usuario` int(11) DEFAULT NULL COMMENT 'Almacena el identificador del usuario que realizó la publicación',
  `fechapublicacion` date DEFAULT NULL COMMENT 'Almacena la fecha de la publicación',
  `estado` int(11) DEFAULT NULL COMMENT 'Almacena el estado de la publicación, que puede ser desde activo, bloqueado o demas'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena la información de los comentarios realizados por los usuarios sobre las distintas publicaciones a la base de datos';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `etiqueta`
--

CREATE TABLE `etiqueta` (
  `id` int(11) NOT NULL,
  `usuario` int(11) DEFAULT NULL COMMENT 'Almacena el identificador del usuario que etiqueto',
  `publicacion` int(11) DEFAULT NULL COMMENT 'Almacena el identificador de la publicacion donde fue etiquetado, se asume que solo puede etiquetar el usuario dueño de la publicacion',
  `fecha` date DEFAULT NULL COMMENT 'Almacena la fecha de la etiqueta',
  `mensaje` text COMMENT 'Almacena el mensaje de la etiqueta'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena la información de las etiquetas de los usuarios sobre las publicaciones';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL,
  `fechamensaje` date DEFAULT NULL,
  `envia` int(11) NOT NULL COMMENT 'Corresponde al identificador del usuario que envia',
  `recibe` int(11) NOT NULL COMMENT 'Corresponde al identificador del usuario que recibe',
  `mensaje` text COMMENT 'Corresponde al mensaje enviado'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena los mensaje privados que son enviados a los usuarios';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

CREATE TABLE `publicacion` (
  `id` int(11) NOT NULL,
  `descripcion` text,
  `usuario` int(11) DEFAULT NULL COMMENT 'Almacena el usuario que realiza la publicación',
  `fechapublicacion` date DEFAULT NULL COMMENT 'Almacena la fecha de publicación del usuario',
  `acceso` int(11) DEFAULT NULL COMMENT 'Define el tipo de acceso de la publicación, que puede ser privado, publico o solo para amigos.',
  `imagen` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Almacena la información de las publicaciones del usuario';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL COMMENT 'Almacena el identificador interno del usuario, es un codigo interno del sistema-',
  `usuario` varchar(50) NOT NULL COMMENT 'Almacena el nombre identificador del usuario',
  `email` varchar(100) NOT NULL COMMENT 'Almacena el correo electrónico del usuario. El email debe ser unico.',
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `pass` varchar(100) NOT NULL,
  `fecharegistro` date DEFAULT NULL COMMENT 'Almacena la fecha de registro del usuario.  Esta fecha es generada por el sistema de forma automatica',
  `fechanacimiento` date DEFAULT NULL COMMENT 'Almacena la fecha de nacimiento del usuario',
  `pais` int(11) DEFAULT NULL COMMENT 'Registra el país de procedencia del usuario'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='El usuario debe ser unico para todos los participantes en la red';

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `email`, `nombre`, `apellido`, `pass`, `fecharegistro`, `fechanacimiento`, `pais`) VALUES
(1, 'admin', 'admin@admin.com', 'edwar', 'camargo', '1234', '2020-04-14', '2020-04-15', 1),
(2, 'yo', 'yo@yo.com', 'yoyo', 'yoyoyo', '1234', '2020-04-15', '2020-04-08', 3),
(3, 'crangarita2', 'crangarita2@gmail.com', 'Carlos', 'Angarita', '1234', NULL, NULL, 0),
(4, 'crangarita', 'crangarita@gmail.com', 'Carlos', 'Angarita', '1234', NULL, NULL, 0),
(5, 'victor', 'a@a.com', NULL, NULL, 'vea', NULL, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amistad`
--
ALTER TABLE `amistad`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_amistad` (`usuario`,`amistad`,`estado`),
  ADD KEY `FK_amistad_usuario_02` (`amistad`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_comentario_publicacion` (`publicacion`),
  ADD KEY `FK_comentario_usuario` (`usuario`);

--
-- Indices de la tabla `etiqueta`
--
ALTER TABLE `etiqueta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_etiqueta_publicacion` (`publicacion`),
  ADD KEY `FK_etiqueta_usuario` (`usuario`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_mensaje_usuario` (`envia`),
  ADD KEY `FK_mensaje_usuario_02` (`recibe`);

--
-- Indices de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_publicacion_usuario` (`usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amistad`
--
ALTER TABLE `amistad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `etiqueta`
--
ALTER TABLE `etiqueta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Almacena el identificador interno del usuario, es un codigo interno del sistema-', AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `amistad`
--
ALTER TABLE `amistad`
  ADD CONSTRAINT `FK_amistad_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_amistad_usuario_02` FOREIGN KEY (`amistad`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `FK_comentario_publicacion` FOREIGN KEY (`publicacion`) REFERENCES `publicacion` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_comentario_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `etiqueta`
--
ALTER TABLE `etiqueta`
  ADD CONSTRAINT `FK_etiqueta_publicacion` FOREIGN KEY (`publicacion`) REFERENCES `publicacion` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_etiqueta_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `FK_mensaje_usuario` FOREIGN KEY (`envia`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_mensaje_usuario_02` FOREIGN KEY (`recibe`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD CONSTRAINT `FK_publicacion_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
