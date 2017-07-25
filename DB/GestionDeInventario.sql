-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 21-07-2017 a las 17:40:55
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
-- Estructura de tabla para la tabla `Clientes`
--

CREATE TABLE `Clientes` (
  `cid` int(11) NOT NULL,
  `clientecodigo` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `Clientes`
--

INSERT INTO `Clientes` (`cid`, `clientecodigo`, `nombre`, `ubicacion`, `telefono`) VALUES
(2, 'cus3', 'juan', 'cordoba', '331'),
(62, 'cliente codigo', 'juan nombre', 'ubicacion 213', '3123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Comprainfo`
--

CREATE TABLE `Comprainfo` (
  `compraid` int(11) NOT NULL,
  `codigoproveedor` varchar(200) NOT NULL,
  `codigoproducto` varchar(200) NOT NULL,
  `fecha` varchar(200) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `costototal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `Comprainfo`
--

INSERT INTO `Comprainfo` (`compraid`, `codigoproveedor`, `codigoproducto`, `fecha`, `cantidad`, `costototal`) VALUES
(77, 'codigoProveedor31', 'producto codigo 1', 'Fri Jan 16 23:12:40 NPT 2016', 2, 55),
(78, 'codigoProveedor31', 'producto codigo', 'Fri Jan 16 23:12:40 NPT 2016', 2, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Herramientas`
--

CREATE TABLE `Herramientas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `fechacompra` varchar(1000) DEFAULT NULL,
  `aloja` varchar(1000) DEFAULT NULL,
  `ubicacion` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Productos`
--

CREATE TABLE `Productos` (
  `id` int(11) NOT NULL,
  `productocodigo` varchar(100) NOT NULL,
  `productonombre` varchar(50) NOT NULL,
  `preciocosto` double NOT NULL,
  `precioventa` double NOT NULL,
  `marca` varchar(50) NOT NULL,
  `stockactual` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proveedores`
--

CREATE TABLE `Proveedores` (
  `ID` int(11) NOT NULL,
  `codigoproveedor` varchar(100) NOT NULL,
  `nombrecompleto` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `Proveedores`
--

INSERT INTO `Proveedores` (`ID`, `codigoproveedor`, `nombrecompleto`, `ubicacion`, `telefono`) VALUES
(69, 'proveedor5', 'la ganadera', 'Alta Gracia', '4123372'),
(68, 'proveedor4', 'mundo limpieza', 'oncativo', '11623231');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reportesventas`
--

CREATE TABLE `Reportesventas` (
  `ventasid` int(11) NOT NULL,
  `fecha` varchar(40) NOT NULL,
  `codigoproducto` varchar(100) NOT NULL,
  `codigocliente` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `ingresos` double NOT NULL,
  `vendidopor` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `Reportesventas`
--

INSERT INTO `Reportesventas` (`ventasid`, `fecha`, `codigoproducto`, `codigocliente`, `cantidad`, `ingresos`, `vendidopor`) VALUES
(1, 'Fri Jan 16 23:12:40 NPT 2015', 'prod2', 'cus3', 4, 120, 'usuario4'),
(2, 'Thu Jan 08 21:30:51 NPT 2015', 'prod1', 'cus3', 5, 2250, 'juanpablo'),
(3, 'Thu Jan 15 21:26:47 NPT 2015', 'prod1', 'cus3', 5, 2250, 'juanpablo'),
(4, 'Sat Jan 17 10:08:20 NPT 2015', 'prod3', 'cus3', 1, 2, 'usuario4'),
(60, 'Fri Jan 16 23:12:40 NPT 2016', 'codigo producto', 'codigo cliente', 21, 24, 'vendedor juan'),
(61, 'Fri Jan 16 23:12:40 NPT 2016', 'codigo producto', 'codigo cliente', 21, 24, 'vendedor juan'),
(62, 'Fri Jan 16 23:12:40 NPT 2016', 'codigo producto', 'codigo cliente', 21, 24, 'vendedor juan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Stockactual`
--

CREATE TABLE `Stockactual` (
  `productocodigo` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `Stockactual`
--

INSERT INTO `Stockactual` (`productocodigo`, `cantidad`) VALUES
('p2', 30),
('p1', 1),
('p10', 0),
('prod1', 0),
('prod2', -10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--

CREATE TABLE `Usuarios` (
  `ID` int(11) NOT NULL,
  `NOMBRECOMPLETO` varchar(50) NOT NULL,
  `UBICACION` varchar(50) NOT NULL,
  `TELEFONO` varchar(10) NOT NULL,
  `USUARIONOMBRE` varchar(20) NOT NULL,
  `CONTRASENA` varchar(200) NOT NULL,
  `CATEGORIA` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `Usuarios`
--

INSERT INTO `Usuarios` (`ID`, `NOMBRECOMPLETO`, `UBICACION`, `TELEFONO`, `USUARIONOMBRE`, `CONTRASENA`, `CATEGORIA`) VALUES
(54, 'juan pablo cuello', 'cordoba', '3513220999', 'usuario4', '1234', 'ADMINISTRATOR'),
(56, 'laura', 'cordoba', '9849284991', 'usuario5', '1234', 'NORMAL USER'),
(57, 'alexis', 'cordoba', '98239832', 'usuario6', '1234', 'NORMAL USER'),
(63, 'juan insertado', 'cordoba', '452234', 'junacito', 'holahola', 'ADMINISTRADOR');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Clientes`
--
ALTER TABLE `Clientes`
  ADD PRIMARY KEY (`cid`);

--
-- Indices de la tabla `Comprainfo`
--
ALTER TABLE `Comprainfo`
  ADD PRIMARY KEY (`compraid`);

--
-- Indices de la tabla `Herramientas`
--
ALTER TABLE `Herramientas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Productos`
--
ALTER TABLE `Productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Proveedores`
--
ALTER TABLE `Proveedores`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Reportesventas`
--
ALTER TABLE `Reportesventas`
  ADD PRIMARY KEY (`ventasid`);

--
-- Indices de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Clientes`
--
ALTER TABLE `Clientes`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
--
-- AUTO_INCREMENT de la tabla `Comprainfo`
--
ALTER TABLE `Comprainfo`
  MODIFY `compraid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
--
-- AUTO_INCREMENT de la tabla `Herramientas`
--
ALTER TABLE `Herramientas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
--
-- AUTO_INCREMENT de la tabla `Productos`
--
ALTER TABLE `Productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
--
-- AUTO_INCREMENT de la tabla `Proveedores`
--
ALTER TABLE `Proveedores`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
--
-- AUTO_INCREMENT de la tabla `Reportesventas`
--
ALTER TABLE `Reportesventas`
  MODIFY `ventasid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
--
-- AUTO_INCREMENT de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;