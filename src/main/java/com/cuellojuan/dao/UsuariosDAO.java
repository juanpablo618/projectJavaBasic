package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Usuarios;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class UsuariosDAO extends GenericDAOImpl<Usuarios> {

    public UsuariosDAO() {
    }


    public void remove(Usuarios entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Usuarios entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Usuarios entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Usuarios find(Usuarios entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {

        return super.find(entity);
    }


}
