package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.dao.impl.GenericDAOImplHotel;
import com.cuellojuan.entity.Usuario;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class UsuariosDAO extends GenericDAOImpl<Usuario> {

    public UsuariosDAO() {
    }


    public void remove(Usuario entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Usuario entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Usuario entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Usuario find(Usuario entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {

        return super.find(entity);
    }


}
