package com.cuellojuan.dao;

import com.cuellojuan.entity.Cliente;
import com.cuellojuan.dao.impl.GenericDAOImpl;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ClientesDAO extends GenericDAOImpl<Cliente> {

    public ClientesDAO() {
    }

    public void remove(Cliente entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Cliente entity) throws IllegalAccessException, SQLException, NoSuchMethodException, NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Cliente entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Cliente find(Cliente entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {


        return super.find(entity);
    }


}
