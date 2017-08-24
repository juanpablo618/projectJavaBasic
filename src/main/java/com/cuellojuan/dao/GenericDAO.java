package com.cuellojuan.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public interface GenericDAO<E> {
        void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException;
        void update(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException;
        void remove(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException;

        E find (E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException;


}
