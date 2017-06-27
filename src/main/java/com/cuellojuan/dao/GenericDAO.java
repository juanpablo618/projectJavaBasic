package com.cuellojuan.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public interface GenericDAO<E> {
        void insert(E entity) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException;
        void update(E entity) throws SQLException, IllegalAccessException, NoSuchFieldException;
        void remove(E entity) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException;

        E find (E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException;


}
