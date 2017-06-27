package com.cuellojuan;

import com.cuellojuan.dao.AlumnoDAO;
import com.cuellojuan.dao.CustomerDAO;
import com.cuellojuan.dao.GenericDAO;
import com.cuellojuan.entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class App {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

//        System.out.println("Con Customer DAO: ");
//        System.out.println(customerDAO.findById(1));
//
//
//        AlumnoDAO alumnoDAO = (AlumnoDAO) context.getBean("alumnoDAO");
//        System.out.println("Con Alumno DAO: ");
//        System.out.println(alumnoDAO.findById(1).toString());


        Customer juan = new Customer();
        int id = 92;

        juan.setId(id);
        juan.setAge(12);
        juan.setName("juan pablo");

        customerDAO.insert(juan);

        juan.setName("roberto carlos");
        customerDAO.update(juan);

        System.out.println(customerDAO.find(juan).toString());


//        Customer juanP = new Customer();
//         int id = 93;
//
//        juanP.setId(id);
//        juanP.setAge(41);
//        juanP.setName("juan pablo");



        System.out.println("clase: " + customerDAO.find(juan).getClass().toString());

       // System.out.println("clase: " + customerDAO.find(juanP).toString());


        //customerDAO.remove(juan);

    }

}
