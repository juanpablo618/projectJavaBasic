package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Estado;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class EstadoDAO extends GenericDAOImpl<Estado> {

    private EstadoDAO() {
    }

    public void remove(Estado entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        super.remove(entity);
    }

    public void insert(Estado entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Estado entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public Estado find(Estado entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }




}
