CREATE DATABASE IF NOT EXISTS `bdproyectojavabasico` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdproyectojavabasico`;
--
-- Estructura de tabla para la tabla `apartamento`
--

CREATE TABLE `apartamento` (
  `id` int(11) NOT NULL,
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
  `id_apartamento` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_nacimiento` varchar(1000) NOT NULL,
  `tel_fijo` varchar(50) NOT NULL,
  `tel_celular` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  `receptor` int(11) DEFAULT NULL,
  `fecha_insercion` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `elementoinventario`
--

CREATE TABLE `elementoinventario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `codigo` varchar(500) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `fecha_insercion` varchar(250) NOT NULL,
  `comprador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `elementoinventario_por_apartamento`
--

CREATE TABLE `elementoinventario_por_apartamento` (
  `id_elementoinventario` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `descripcion` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provreserva`
--

CREATE TABLE `provreserva` (
  `id` int(11) NOT NULL,
  `nombre` varchar(1000) NOT NULL,
  `descripcion` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `num_personas` int(11) NOT NULL,
  `tarifa_total` double NOT NULL,
  `comentario` varchar(500) NOT NULL,
  `proveedor` int(11) DEFAULT NULL,
  `ingreso` varchar(100) NOT NULL,
  `egreso` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `codigo` varchar(200) DEFAULT NULL,
  `descripcion` varchar(500) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `vendedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_por_apartamento`
--

CREATE TABLE `servicio_por_apartamento` (
  `id_servicio` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

CREATE TABLE `tarea` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `realizador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea_por_apartamento`
--

CREATE TABLE `tarea_por_apartamento` (
  `id_tarea` int(11) NOT NULL,
  `id_apartamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
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
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `apartamento_por_reserva`
--
ALTER TABLE `apartamento_por_reserva`
  ADD KEY `apartamento_por_reserva-apartamento` (`id_apartamento`),
  ADD KEY `apartamento_por_reserva-reserva` (`id_reserva`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receptor` (`receptor`);

--
-- Indices de la tabla `elementoinventario`
--
ALTER TABLE `elementoinventario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comprador` (`comprador`);

--
-- Indices de la tabla `elementoinventario_por_apartamento`
--
ALTER TABLE `elementoinventario_por_apartamento`
  ADD KEY `elementoinventario_por_apartamento-elementoinventario` (`id_elementoinventario`),
  ADD KEY `elementoinventario_por_apartamento-apartamento` (`id_apartamento`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `provreserva`
--
ALTER TABLE `provreserva`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proveedor` (`proveedor`),
  ADD KEY `reserva-cliente` (`cliente`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `servicio-estado` (`estado`),
  ADD KEY `vendedor` (`vendedor`);

--
-- Indices de la tabla `servicio_por_apartamento`
--
ALTER TABLE `servicio_por_apartamento`
  ADD KEY `servicio_por_apartamento-servicio` (`id_servicio`),
  ADD KEY `servicio_por_apartamento-apartamento` (`id_apartamento`);

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `estado` (`estado`),
  ADD KEY `realizador` (`realizador`);

--
-- Indices de la tabla `tarea_por_apartamento`
--
ALTER TABLE `tarea_por_apartamento`
  ADD KEY `tarea_por_apartamento-apartamento` (`id_apartamento`),
  ADD KEY `tarea_por_apartamento-tarea` (`id_tarea`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apartamento`
--
ALTER TABLE `apartamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `elementoinventario`
--
ALTER TABLE `elementoinventario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `provreserva`
--
ALTER TABLE `provreserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apartamento_por_reserva`
--
ALTER TABLE `apartamento_por_reserva`
  ADD CONSTRAINT `apartamento_por_reserva-apartamento` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `apartamento_por_reserva-reserva` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente-usuario` FOREIGN KEY (`receptor`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `elementoinventario`
--
ALTER TABLE `elementoinventario`
  ADD CONSTRAINT `elementoinventario-usuario` FOREIGN KEY (`comprador`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `elementoinventario_por_apartamento`
--
ALTER TABLE `elementoinventario_por_apartamento`
  ADD CONSTRAINT `elementoinventario_por_apartamento-apartamento` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `elementoinventario_por_apartamento-elementoinventario` FOREIGN KEY (`id_elementoinventario`) REFERENCES `elementoinventario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva-cliente` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserva-provreserva` FOREIGN KEY (`proveedor`) REFERENCES `provreserva` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `servicio-estado` FOREIGN KEY (`estado`) REFERENCES `estado` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `servicio-usuario` FOREIGN KEY (`vendedor`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicio_por_apartamento`
--
ALTER TABLE `servicio_por_apartamento`
  ADD CONSTRAINT `servicio_por_apartamento-apartamento` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `servicio_por_apartamento-servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD CONSTRAINT `tarea-estado` FOREIGN KEY (`estado`) REFERENCES `estado` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `tarea-usuario` FOREIGN KEY (`realizador`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `tarea_por_apartamento`
--
ALTER TABLE `tarea_por_apartamento`
  ADD CONSTRAINT `tarea_por_apartamento-apartamento` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tarea_por_apartamento-tarea` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
