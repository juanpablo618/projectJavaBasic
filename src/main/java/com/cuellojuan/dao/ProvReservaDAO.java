package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.ProvReserva;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProvReservaDAO extends GenericDAOImpl<ProvReserva> {

    public void remove(ProvReserva entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        super.remove(entity);
    }

    public void insert(ProvReserva entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(ProvReserva entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }

    public ProvReserva find(ProvReserva entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.find(entity);
    }

    public ProvReserva findByProperty(String field , String value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.findByProperty(field, value);
    }

}
