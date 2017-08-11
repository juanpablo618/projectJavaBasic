package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.ProvReserva;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class ProveedorReservaDAO extends GenericDAOImpl<ProvReserva> {

    public ProveedorReservaDAO() {
    }

    public void remove(ProvReserva entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(ProvReserva entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException {
        super.insert(entity);
    }

    public void update(ProvReserva entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public ProvReserva find(ProvReserva entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {


        return super.find(entity);
    }








}
