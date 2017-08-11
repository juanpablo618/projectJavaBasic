package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImplHotel;
import com.cuellojuan.entity.Apartamento;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


public class ReporteInventarioDAO extends GenericDAOImplHotel<Apartamento> {

    public ReporteInventarioDAO() {
    }

    public int obtenerUltimoId(String id, String tabla) throws SQLException {
        return super.obtenerUltimoId(id, tabla);

    }

    public int obtenerCantidadDePiezasPorLimpiar() throws SQLException {
        return super.obtenerCantidadDePiezasPorLimpiar();

    }


}
