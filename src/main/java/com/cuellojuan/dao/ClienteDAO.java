package com.cuellojuan.dao;

import com.cuellojuan.entity.Cliente;
import com.cuellojuan.dao.impl.GenericDAOImpl;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ClienteDAO extends GenericDAOImpl<Cliente> {

    public ClienteDAO() {
    }

    public void remove(Cliente entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Cliente entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        super.insert(entity);
    }

    public void update(Cliente entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        super.update(entity);
    }


    public Cliente find(Cliente entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException {


        return super.find(entity);
    }


}