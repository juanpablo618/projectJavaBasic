package com.cuellojuan.dao;


import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Productos;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProductosDAO extends GenericDAOImpl<Productos> {

    public ProductosDAO() {
    }


    public void remove(Productos entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Productos entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Productos entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Productos find(Productos entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {
        return super.find(entity);
    }

}
