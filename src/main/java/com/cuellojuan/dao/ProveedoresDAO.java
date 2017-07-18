package com.cuellojuan.dao;


import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Proveedores;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProveedoresDAO extends GenericDAOImpl<Proveedores> {


    public ProveedoresDAO() {
    }


    public void remove(Proveedores entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Proveedores entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Proveedores entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Proveedores find(Proveedores entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {
        return super.find(entity);
    }





}
