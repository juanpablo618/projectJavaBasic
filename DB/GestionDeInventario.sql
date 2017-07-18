-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 18-07-2017 a las 16:47:47
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `GestionDeInventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cid` int(11) NOT NULL,
  `clientecodigo` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cid`, `clientecodigo`, `nombre`, `ubicacion`, `telefono`) VALUES
(2, 'cus3', 'juan', 'cordoba', '331');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprainfo`
--

CREATE TABLE `comprainfo` (
  `compraid` int(11) NOT NULL,
  `codigoproveedor` varchar(200) NOT NULL,
  `codigoproducto` varchar(200) NOT NULL,
  `fecha` varchar(200) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `costototal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comprainfo`
--

INSERT INTO `comprainfo` (`compraid`, `codigoproveedor`, `codigoproducto`, `fecha`, `cantidad`, `costototal`) VALUES
(19, 's1', 'p2', 'Wed Jan 14 00:15:19 NPT 2015', 40, 1320),
(20, 's1', 'p1', 'Wed Jan 07 16:42:44 NPT 2015', 4, 80000),
(21, 's6', 'p10', 'Tue Jan 06 16:51:44 NPT 2015', 20, 400000),
(22, 'proveedor4', 'prod1', 'Thu Jan 15 15:25:45 NPT 2015', 4, 1600),
(23, 'proveedor5', 'prod1', 'Thu Jan 15 00:00:00 NPT 2015', 6, 2400),
(29, 'proveedor4', 'prod2', 'Fri Jan 16 23:09:17 NPT 2015', 5, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `pid` int(11) NOT NULL,
  `productocodigo` varchar(100) NOT NULL,
  `productonombre` varchar(50) NOT NULL,
  `preciocosto` double NOT NULL,
  `precioventa` double NOT NULL,
  `marca` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`pid`, `productocodigo`, `productonombre`, `preciocosto`, `precioventa`, `marca`) VALUES
(73, 'prod3', 'lavandina', 3, 2, '4d'),
(72, 'prod2', 'botella coca cola 2 litros no retornable', 20, 30, 'coca cola'),
(71, 'prod1', 'bolsas de basura chiquitas', 400, 450, 'cg'),
(74, 'prod4', 'milanesas de carne', 400, 450, 'la ganadera'),
(78, 'prod5', 'papel higienico', 500, 700, 'cg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `pid` int(11) NOT NULL,
  `codigoproveedor` varchar(100) NOT NULL,
  `nombrecompleto` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`pid`, `codigoproveedor`, `nombrecompleto`, `ubicacion`, `telefono`) VALUES
(69, 'proveedor5', 'la ganadera', 'Alta Gracia', '4123372'),
(68, 'proveedor4', 'mundo limpieza', 'oncativo', '11623231');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportesventas`
--

CREATE TABLE `reportesventas` (
  `ventasid` int(11) NOT NULL,
  `fecha` varchar(40) NOT NULL,
  `codigoproducto` varchar(100) NOT NULL,
  `codigocliente` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `ingresos` double NOT NULL,
  `vendidopor` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reportesventas`
--

INSERT INTO `reportesventas` (`ventasid`, `fecha`, `codigoproducto`, `codigocliente`, `cantidad`, `ingresos`, `vendidopor`) VALUES
(1, 'Fri Jan 16 23:12:40 NPT 2015', 'prod2', 'cus3', 4, 120, 'usuario4'),
(2, 'Thu Jan 08 21:30:51 NPT 2015', 'prod1', 'cus3', 5, 2250, 'juanpablo'),
(3, 'Thu Jan 15 21:26:47 NPT 2015', 'prod1', 'cus3', 5, 2250, 'juanpablo'),
(4, 'Sat Jan 17 10:08:20 NPT 2015', 'prod3', 'cus3', 1, 2, 'usuario4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stockactual`
--

CREATE TABLE `stockactual` (
  `productocodigo` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `stockactual`
--

INSERT INTO `stockactual` (`productocodigo`, `cantidad`) VALUES
('p2', 30),
('p1', 1),
('p10', 0),
('prod1', 0),
('prod2', -10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuarioid` int(11) NOT NULL,
  `nombrecompleto` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `usuarionombre` varchar(20) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  `categoria` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuarioid`, `nombrecompleto`, `ubicacion`, `telefono`, `usuarionombre`, `contrasena`, `categoria`) VALUES
(54, 'juan pablo cuello', 'cordoba', '3513220999', 'usuario4', '1234', 'ADMINISTRATOR'),
(56, 'laura', 'cordoba', '9849284991', 'usuario5', '1234', 'NORMAL USER'),
(57, 'alexis', 'cordoba', '98239832', 'usuario6', '1234', 'NORMAL USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cid`);

--
-- Indices de la tabla `comprainfo`
--
ALTER TABLE `comprainfo`
  ADD PRIMARY KEY (`compraid`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`pid`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`pid`);

--
-- Indices de la tabla `reportesventas`
--
ALTER TABLE `reportesventas`
  ADD PRIMARY KEY (`ventasid`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuarioid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `comprainfo`
--
ALTER TABLE `comprainfo`
  MODIFY `compraid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;
--
-- AUTO_INCREMENT de la tabla `reportesventas`
--
ALTER TABLE `reportesventas`
  MODIFY `ventasid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuarioid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;