package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Reserva;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ReservaDAO extends GenericDAOImpl<Reserva> {

    public void remove(Reserva entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        super.remove(entity);
    }

    public void insert(Reserva entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Reserva entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }

    public Reserva find(Reserva entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.find(entity);
    }

    public Reserva findByProperty(String field , String value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.findByProperty(field, value);
    }

}
