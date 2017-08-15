package com.cuellojuan.dao;


import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.ElementoInventario;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ElementoInventarioDAO extends GenericDAOImpl<ElementoInventario> {

    public ElementoInventarioDAO() {
    }

    public void remove(ElementoInventario entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(ElementoInventario entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException {
        super.insert(entity);
    }

    public void update(ElementoInventario entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public ElementoInventario find(ElementoInventario entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {


        return super.find(entity);
    }




}
