package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.TareasPorApartamento;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class TareasPorApartamentoDAO  extends GenericDAOImpl<TareasPorApartamento> {

    public TareasPorApartamentoDAO() {
    }

    public void remove(TareasPorApartamento entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(TareasPorApartamento entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(TareasPorApartamento entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public TareasPorApartamento find(TareasPorApartamento entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }



}
