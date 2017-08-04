package com.cuellojuan;

import com.cuellojuan.dao.ReporteInventarioDAO;
import com.cuellojuan.entity.Relinventario;
import com.cuellojuan.tasks.BuscarPiezasConVentiladores;
import com.cuellojuan.tasks.BuscarPiezasSucias;
import com.cuellojuan.tasks.ReservasPorBooking;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class App {

    public static final ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //Cantidad de piezas por limpiar diarias:
        BuscarPiezasSucias hilo1 = new BuscarPiezasSucias();
        hilo1.start();

        //Piezas que tienen ventilador:
        BuscarPiezasConVentiladores hilo2 = new BuscarPiezasConVentiladores();
        hilo2.start();

        //Numero de reservas que vienen por booking:
        ReservasPorBooking hilo3 = new ReservasPorBooking();
        hilo3.start();


//        ReportesventasDAO reportesventasDAO = (ReportesventasDAO) context.getBean("reportesventasDAO");
//
//        Reportesventas reportesVentas = new Reportesventas();
//
//        reportesVentas.setVentasid(id);
//        reportesVentas.setFecha("Fri Jan 16 23:12:40 NPT 2016");
//        reportesVentas.setCodigoproducto("codigo producto");
//        reportesVentas.setCodigocliente("codigo cliente");
//        reportesVentas.setCantidad(21);
//        reportesVentas.setIngresos(24);
//        reportesVentas.setVendidopor("vendedor juan");
//
//        reportesventasDAO.insert(reportesVentas);
//
//
//
//        ProveedoresDAO proveedoresDAO = (ProveedoresDAO) context.getBean("proveedoresDAO");
//
//        Proveedores proveedor = new Proveedores();
//
//        proveedoresDAO.insert(proveedor);
//
//
//
//
//        ComprainfoDAO comprainfoDAO = (ComprainfoDAO) context.getBean("comprainfoDAO");
//
//        Comprainfo comprainfo = new Comprainfo();
//
//        comprainfo.setCompraid(id);
//        comprainfo.setCodigoproveedor("codigo proveedor");
//        comprainfo.setCodigoproducto("codigo producto");
//        comprainfo.setFecha("Fri Jan 16 23:12:40 NPT 2016");
//        comprainfo.setCantidad(21);
//        comprainfo.setCostototal(21.2);
//
//        comprainfoDAO.insert(comprainfo);
//
//        ClientesDAO clientesDAO = (ClientesDAO) context.getBean("clientesDAO");
//
//
//        Cliente cliente = new Cliente();
//        cliente.setCid(45);
//        cliente.setClientecodigo("cliente codigo");
//        cliente.setNombre("juan nombre");
//        cliente.setUbicacion("ubicacion 213");
//        cliente.setTelefono("3123");
//
//        clientesDAO.insert(cliente);
//        clientesDAO.find(cliente).toString();
//


    }

}
