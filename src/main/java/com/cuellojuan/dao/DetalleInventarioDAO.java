package com.cuellojuan.dao;


import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.DetalleInventario;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DetalleInventarioDAO extends GenericDAOImpl<DetalleInventario> {

    public DetalleInventarioDAO() {
    }

    public void remove(DetalleInventario entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(DetalleInventario entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(DetalleInventario entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public DetalleInventario find(DetalleInventario entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }




}
