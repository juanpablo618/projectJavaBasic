package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Comprainfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class ComprainfoDAO extends GenericDAOImpl<Comprainfo> {


    public ComprainfoDAO() {
    }


    public void remove(Comprainfo entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Comprainfo entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Comprainfo entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Comprainfo find(Comprainfo entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {
        return super.find(entity);
    }




}
