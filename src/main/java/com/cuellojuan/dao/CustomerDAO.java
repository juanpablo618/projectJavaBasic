package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.cuellojuan.entity.Customer;

public class CustomerDAO extends GenericDAOImpl<Customer> {

    public CustomerDAO() {
    }


    public void remove(Customer entity) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException {
        super.remove(entity);
    }

    public void insert(Customer entity) throws IllegalAccessException, SQLException, NoSuchMethodException,NoSuchFieldException {
        super.insert(entity);
    }

    public void update(Customer entity) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException {
            super.update(entity);
    }


    public Customer find(Customer entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, SQLException {
        return super.find(entity);
    }
}