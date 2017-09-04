package com.cuellojuan.dao;


import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Apartamento;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ApartamentoDAO  extends GenericDAOImpl<Apartamento> {


    private ApartamentoDAO() {
        super();
    }

    public void remove(Apartamento entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Apartamento entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Apartamento entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public Apartamento find(Apartamento entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }



}
