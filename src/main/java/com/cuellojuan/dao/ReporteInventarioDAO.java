package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImplHotel;
import com.cuellojuan.entity.Piezas;
import com.cuellojuan.entity.Relinventario;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ReporteInventarioDAO extends GenericDAOImplHotel<Relinventario> {

    public ReporteInventarioDAO() {
    }


    public void remove(Relinventario entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Relinventario entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Relinventario entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Relinventario find(Relinventario entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {
        return super.find(entity);
    }

    public int obtenerUltimoId(String id, String tabla) throws SQLException {
        return super.obtenerUltimoId(id, tabla);

    }

    public int obtenerCantidadDePiezasPorLimpiar() throws SQLException {
        return super.obtenerCantidadDePiezasPorLimpiar();

    }

    public List<Piezas> obtenerPiezasConVentiladores() throws SQLException{
        return super.obtenerPiezasConVentiladores();
    }


}
