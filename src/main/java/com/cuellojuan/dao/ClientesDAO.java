package com.cuellojuan.dao;

import com.cuellojuan.entity.Clientes;
import com.cuellojuan.dao.impl.GenericDAOImpl;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ClientesDAO extends GenericDAOImpl<Clientes> {

    public ClientesDAO() {
    }

    public void remove(Clientes entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Clientes entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Clientes entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
        super.update(entity);
    }


    public Clientes find(Clientes entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {


        return super.find(entity);
    }


}
