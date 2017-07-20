package com.cuellojuan;

import com.cuellojuan.dao.*;
import com.cuellojuan.entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class App {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

//        System.out.println("Con Customer DAO: ");
//        System.out.println(customerDAO.findById(1));
//
//
//        AlumnoDAO alumnoDAO = (AlumnoDAO) context.getBean("alumnoDAO");
//        System.out.println("Con Alumno DAO: ");
//        System.out.println(alumnoDAO.findById(1).toString());

//    for (int i = 1 ; i<2 ; i++) {
//        Customer juan = new Customer();
//        int id = i;
//
//        juan.setId(id);
//        juan.setAge(i);
//        juan.setName("juan pablo");
//
//        customerDAO.insert(juan);
//
//        juan.setName("roberto carlos");
//        customerDAO.update(juan);
//
//        System.out.println(customerDAO.find(juan).toString());
//

//        Customer juanP = new Customer();
         int id = 93;
//
//        juanP.setId(id);
//        juanP.setAge(41);
//        juanP.setName("juan pablo");


//        System.out.println("clase: " + customerDAO.find(juan).getClass().toString());

        //System.out.println("clase: " + customerDAO.find(juanP).toString());


        //customerDAO.remove(juan);
    //}


        ReportesventasDAO reportesventasDAO = (ReportesventasDAO) context.getBean("reportesventasDAO");

        Reportesventas reportesVentas = new Reportesventas();

        reportesVentas.setVentasid(id);
        reportesVentas.setFecha("Fri Jan 16 23:12:40 NPT 2016");
        reportesVentas.setCodigoproducto("codigo producto");
        reportesVentas.setCodigocliente("codigo cliente");
        reportesVentas.setCantidad(21);
        reportesVentas.setIngresos(24);
        reportesVentas.setVendidopor("vendedor juan");

        reportesventasDAO.insert(reportesVentas);



        ProveedoresDAO proveedoresDAO = (ProveedoresDAO) context.getBean("proveedoresDAO");

        Proveedores proveedor = new Proveedores();

        proveedoresDAO.insert(proveedor);




        ComprainfoDAO comprainfoDAO = (ComprainfoDAO) context.getBean("comprainfoDAO");

        Comprainfo comprainfo = new Comprainfo();

        comprainfo.setCompraid(id);
        comprainfo.setCodigoproveedor("codigo proveedor");
        comprainfo.setCodigoproducto("codigo producto");
        comprainfo.setFecha("Fri Jan 16 23:12:40 NPT 2016");
        comprainfo.setCantidad(21);
        comprainfo.setCostototal(21.2);

        comprainfoDAO.insert(comprainfo);

        ClientesDAO clientesDAO = (ClientesDAO) context.getBean("clientesDAO");


        Clientes cliente = new Clientes();
        cliente.setCid(id);
        cliente.setClientecodigo("cliente codigo");
        cliente.setNombre("juan nombre");
        cliente.setUbicacion("ubicacion 213");
        cliente.setTelefono("3123");

        clientesDAO.insert(cliente);

    }

}
