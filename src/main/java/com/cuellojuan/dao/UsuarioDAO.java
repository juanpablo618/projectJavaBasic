package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;
import com.cuellojuan.entity.Usuario;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UsuarioDAO extends GenericDAOImpl<Usuario> {

    public void remove(Usuario entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        super.remove(entity);
    }

    public void insert(Usuario entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Usuario entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }

    public Usuario find(Usuario entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.find(entity);
    }

    public Usuario findByProperty(String field , String value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {
        return super.findByProperty(field, value);
    }

}
