package com.cuellojuan.dao.impl;


import com.cuellojuan.entity.Servicio;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ServicioDAO extends GenericDAOImpl<Servicio>{


    public ServicioDAO() {
    }

    public void remove(Servicio entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Servicio entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Servicio entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public Servicio find(Servicio entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }




}
