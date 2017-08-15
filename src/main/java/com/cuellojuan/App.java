package com.cuellojuan;

import com.cuellojuan.dao.*;
import com.cuellojuan.entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;


public class App {

    public static final ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {



// esto no se usaría mas quedo viejo
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

       UsuariosDAO usuarioDAO = (UsuariosDAO)   context.getBean("usuariosDAO");

       ProveedorReservaDAO proveedorReservaDAO = (ProveedorReservaDAO)   context.getBean("proveedorReservaDAO");

        ReservasDAO reservasDAO =  (ReservasDAO) context.getBean("reservasDAO");


        ApartamentoDAO apartamentoDAO = (ApartamentoDAO) context.getBean("apartamentoDAO");


        ElementoInventarioDAO elementoInventarioDAO = (ElementoInventarioDAO) context.getBean("elementoInventarioDAO");

        DetalleInventarioDAO detalleInventarioDAO = (DetalleInventarioDAO) context.getBean("detalleInventarioDAO");

        TareasPorApartamentoDAO tareasPorApartamentoDAO = (TareasPorApartamentoDAO) context.getBean("tareasPorApartamentoDAO");


        TareaDAO tareaDAO = (TareaDAO) context.getBean("tareaDAO");

        EstadoDAO estadoDAO = (EstadoDAO) context.getBean("estadoDAO");



        Usuario juan = new Usuario();
        juan.setId(90);
        juan.setNombre("juan pablo");
        juan.setApellido("cuello");

        usuarioDAO.insert(juan);


        Cliente cliente = new Cliente();

        cliente.setId(5);
        cliente.setNombre("juan");
        cliente.setApellido("cuello");


        cliente.setFecha_nacimiento(Calendar.getInstance().getTime().toString());


        cliente.setTel_fijo("21312");
        cliente.setTel_celular("121212");
        cliente.setEmail("email@email.com");
        cliente.setComentario("comentario");



        cliente.setUsuarioquerecibio(juan);
        cliente.setFecha_insercion(Calendar.getInstance().getTime().toString());

        clienteDAO.insert(cliente);



        ProvReserva provedorBooking = new ProvReserva();

        provedorBooking.setId(2);
        provedorBooking.setNombre("booking");
        provedorBooking.setDescripcion("booking por extranet");

         proveedorReservaDAO.insert(provedorBooking);



        Reserva reserva = new Reserva();

        reserva.setId(2);
        reserva.setCliente(cliente);
        reserva.setNum_personas(5);
        reserva.setTarifa_total(200.00);
        reserva.setComentario("comentario en la reserva");
        reserva.setProveedorDeReserva(provedorBooking);

        reservasDAO.insert(reserva);


        Apartamento apartamento1 = new Apartamento();

        apartamento1.setId(2);
        apartamento1.setNro_piso(2);
        apartamento1.setOcupacion_maxima(4);
        apartamento1.setNombre_edificio("la porteña");
        apartamento1.setComentario("facil acceso directo desde la calle.");

        apartamentoDAO.insert(apartamento1);


        ElementoInventario pala = new ElementoInventario();
        pala.setId(1);
        pala.setNombre("pala ancha");
        pala.setCodigo("xaaw21");
        pala.setDescripcion("pala para pozos");
        pala.setFecha_insercion(Calendar.getInstance().getTime().toString());
        pala.setUsuarioQueCompro(juan);

        elementoInventarioDAO.insert(pala);


        DetalleInventario detalleInventario = new DetalleInventario();
        detalleInventario.setElementoInventario(pala);
        detalleInventario.setApartamento(apartamento1);
        detalleInventario.setCantidad(2);
        detalleInventario.setFecha_insercion(Calendar.getInstance().getTime().toString());
        detalleInventario.setRealizadoPor(juan);


        detalleInventarioDAO.insert(detalleInventario);


        Tarea limpiar = new Tarea();
        limpiar.setNombre("limpiar pieza");
        limpiar.setDescripcion("limpiar todo hasta armarios");
        limpiar.setId(2);


        tareaDAO.insert(limpiar);


        Estado realizado = new Estado();
        realizado.setId(8);
        realizado.setNombre("realizado");
        realizado.setDescripcion("totalmente completo");

        estadoDAO.insert(realizado);


        TareasPorApartamento limpiarPieza1 = new TareasPorApartamento();

        limpiarPieza1.setTarea(limpiar);
        limpiarPieza1.setApartamento(apartamento1);
        limpiarPieza1.setEstado(realizado);
        limpiarPieza1.setRealizarPor(juan);
        limpiarPieza1.setDescripcion("debe realizarse antes de las 12 am");


        tareasPorApartamentoDAO.insert(limpiarPieza1);


    }






}