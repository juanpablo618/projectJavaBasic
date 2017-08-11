package com.cuellojuan;

import com.cuellojuan.dao.ClientesDAO;
import com.cuellojuan.dao.ProveedorReservaDAO;
import com.cuellojuan.dao.UsuariosDAO;
import com.cuellojuan.entity.Cliente;
import com.cuellojuan.entity.ProvReserva;
import com.cuellojuan.entity.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;


public class App {

    public static final ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {




        //Cantidad de piezas por limpiar diarias:
//        BuscarPiezasSucias hilo1 = new BuscarPiezasSucias();
//        hilo1.start();
//
//        //Piezas que tienen ventilador:
//        BuscarPiezasConVentiladores hilo2 = new BuscarPiezasConVentiladores();
//        hilo2.start();
//
//        //Numero de reservas que vienen por booking:
//        ReservasPorBooking hilo3 = new ReservasPorBooking();
//        hilo3.start();
//
//
//
       ClientesDAO clienteDAO = (ClientesDAO) context.getBean("clientesDAO");

       //UsuariosDAO usuarioDAO = (UsuariosDAO)   context.getBean("usuarioDAO");

        Usuario juan = new Usuario();
        juan.setId_usuario(1);
        juan.setNombre("juan pablo");
        juan.setApellido("cuello");


        //usuarioDAO.find(juan);

        Cliente cliente = new Cliente();

        cliente.setId(5);
        cliente.setNombre("juan");
        cliente.setApellido("cuello");


        cliente.setFecha_nacimiento(Calendar.getInstance().getTime().toString());


        cliente.setTel_fijo("21312");
        cliente.setTel_celular("121212");
        cliente.setEmail("email@email.com");
        cliente.setComentario("comentario");



        //falta usuario que lo recibió
        cliente.setUsuarioquerecibio(juan);
        cliente.setFecha_insercion(Calendar.getInstance().getTime().toString());

        clienteDAO.insert(cliente);


}
}