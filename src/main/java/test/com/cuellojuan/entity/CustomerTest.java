package test.com.cuellojuan.entity;

import static org.junit.Assert.assertEquals;

import com.cuellojuan.dao.CustomerDAO;


import com.cuellojuan.entity.Customer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CustomerTest {

    @Test
    public void testGettersFromCustomer(){
        com.cuellojuan.entity.Customer customer = new Customer();

        customer.setId(1);
        customer.setAge(27);
        customer.setName("juanPablo");

        assertEquals(customer.getCustId(),1);
        assertEquals(customer.getAge(),27);
        assertEquals(customer.getName(),"juanPablo");
    }


    @Test
    public void testCustomerInsertAndFindById() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

        Customer customer = new Customer();
        customer.setId(999);
        customer.setName("juan test unitario");
        customer.setAge(87);

        customerDAO.insert(customer);

        assertEquals(999,customerDAO.find(customer).getCustId());

        customerDAO.remove(customer);
    }





    @Test
    public void testCustomerInsertAndUpdate() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

        int nroid = 999;
        String name = "juan";
        int age= 67;

        Customer customerTest = new Customer(nroid,name, age);

        customerDAO.insert(customerTest);

        assertEquals(" juan ",customerDAO.find(customerTest).getName());

        name="robertoCarlosTest";
        customerTest.setName(name);

        customerDAO.update(customerTest);

        assertEquals(nroid,customerDAO.find(customerTest).getCustId());
        assertEquals(age,customerDAO.find(customerTest).getAge());
        assertEquals(" "+name+" ",customerDAO.find(customerTest).getName());

        customerDAO.remove(customerTest);

    }

    @Test
    public  void testCustomerDelete() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

        int nroid = 999;
        String name = "juan";
        int age= 67;

        Customer customerTest = new Customer(nroid,name,age);

        customerDAO.insert(customerTest);
        customerDAO.remove(customerTest);

        assertEquals(null, customerDAO.find(customerTest));

    }

}
