-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 04-08-2017 a las 23:03:42
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdproyectojavabasico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartamento`
--

CREATE TABLE `apartamento` (
  `id_apartamento` int(11) NOT NULL,
  `nro_piso` int(11) NOT NULL,
  `ocupacion_maxima` int(11) NOT NULL,
  `nombre_edificio` varchar(500) NOT NULL,
  `comentario` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartamento_por_reserva`
--

CREATE TABLE `apartamento_por_reserva` (
  `id_reserva` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `tel_fijo` varchar(50) NOT NULL,
  `tel_celular` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  `recibido_por` int(11) NOT NULL,
  `fecha_insercion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_inventario`
--

CREATE TABLE `detalle_inventario` (
  `id_elemento_de_inventario` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `elementos_de_inventario`
--

CREATE TABLE `elementos_de_inventario` (
  `id_elemento_de_inventario` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `codigo` varchar(500) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `fecha_insercion` date NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estado` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `descripcion` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores_de_reserva`
--

CREATE TABLE `proveedores_de_reserva` (
  `id_prov_de_reserva` int(11) NOT NULL,
  `nombre` varchar(1000) NOT NULL,
  `descripcion` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `num_personas` int(11) NOT NULL,
  `tarifa_total` double NOT NULL,
  `comentario` int(11) NOT NULL,
  `id_prov_de_reserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_servicio` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `codigo` varchar(200) NOT NULL,
  `descripcion` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_por_apartamento`
--

CREATE TABLE `servicio_por_apartamento` (
  `id_servicio` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_y_hora_de_ingreso` datetime NOT NULL,
  `usuario_tomo_el_pedido` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `id_tarea` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `descripcion` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas_por_apartamento`
--

CREATE TABLE `tareas_por_apartamento` (
  `id_tarea` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL,
  `usuario a realizarla` varchar(250) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `id_usuario_a_realizarla` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apartamento`
--
ALTER TABLE `apartamento`
  ADD PRIMARY KEY (`id_apartamento`);

--
-- Indices de la tabla `apartamento_por_reserva`
--
ALTER TABLE `apartamento_por_reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD UNIQUE KEY `id_reserva_2` (`id_reserva`),
  ADD UNIQUE KEY `id_apartamento` (`id_apartamento`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `recibido_por` (`recibido_por`);

--
-- Indices de la tabla `detalle_inventario`
--
ALTER TABLE `detalle_inventario`
  ADD KEY `detalle_inventario_ibfk_1` (`id_elemento_de_inventario`),
  ADD KEY `detalle_inventario_ibfk_2` (`id_apartamento`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `elementos_de_inventario`
--
ALTER TABLE `elementos_de_inventario`
  ADD PRIMARY KEY (`id_elemento_de_inventario`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `proveedores_de_reserva`
--
ALTER TABLE `proveedores_de_reserva`
  ADD PRIMARY KEY (`id_prov_de_reserva`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_prov_de_reserva` (`id_prov_de_reserva`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `servicio_por_apartamento`
--
ALTER TABLE `servicio_por_apartamento`
  ADD KEY `id_servicio` (`id_servicio`),
  ADD KEY `id_apartamento` (`id_apartamento`),
  ADD KEY `id_estado` (`id_estado`),
  ADD KEY `usuario_tomo_el_pedido` (`usuario_tomo_el_pedido`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`id_tarea`);

--
-- Indices de la tabla `tareas_por_apartamento`
--
ALTER TABLE `tareas_por_apartamento`
  ADD KEY `id_tarea` (`id_tarea`),
  ADD KEY `id_apartamento` (`id_apartamento`),
  ADD KEY `id_estado` (`id_estado`),
  ADD KEY `id_usuario_a_realizarla` (`id_usuario_a_realizarla`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `id_tarea` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apartamento_por_reserva`
--
ALTER TABLE `apartamento_por_reserva`
  ADD CONSTRAINT `apartamento_por_reserva_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `apartamento_por_reserva_ibfk_2` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id_apartamento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`recibido_por`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_inventario`
--
ALTER TABLE `detalle_inventario`
  ADD CONSTRAINT `detalle_inventario_ibfk_1` FOREIGN KEY (`id_elemento_de_inventario`) REFERENCES `elementos_de_inventario` (`id_elemento_de_inventario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detalle_inventario_ibfk_2` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id_apartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detalle_inventario_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `elementos_de_inventario`
--
ALTER TABLE `elementos_de_inventario`
  ADD CONSTRAINT `elementos_de_inventario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_prov_de_reserva`) REFERENCES `proveedores_de_reserva` (`id_prov_de_reserva`);

--
-- Filtros para la tabla `servicio_por_apartamento`
--
ALTER TABLE `servicio_por_apartamento`
  ADD CONSTRAINT `servicio_por_apartamento_ibfk_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`),
  ADD CONSTRAINT `servicio_por_apartamento_ibfk_2` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id_apartamento`),
  ADD CONSTRAINT `servicio_por_apartamento_ibfk_3` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  ADD CONSTRAINT `servicio_por_apartamento_ibfk_4` FOREIGN KEY (`usuario_tomo_el_pedido`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `tareas_por_apartamento`
--
ALTER TABLE `tareas_por_apartamento`
  ADD CONSTRAINT `tareas_por_apartamento_ibfk_1` FOREIGN KEY (`id_tarea`) REFERENCES `tareas` (`id_tarea`),
  ADD CONSTRAINT `tareas_por_apartamento_ibfk_2` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id_apartamento`),
  ADD CONSTRAINT `tareas_por_apartamento_ibfk_3` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  ADD CONSTRAINT `tareas_por_apartamento_ibfk_4` FOREIGN KEY (`id_usuario_a_realizarla`) REFERENCES `usuarios` (`id_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
