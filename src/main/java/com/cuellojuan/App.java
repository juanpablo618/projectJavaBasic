package com.cuellojuan;

import com.cuellojuan.dao.*;
import com.cuellojuan.dao.ApartamentoDAO;
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
       ClienteDAO clienteDAO = (ClienteDAO) context.getBean("ClienteDAO");
       UsuarioDAO usuarioDAO = (UsuarioDAO)   context.getBean("UsuarioDAO");
       ProvReservaDAO provReservaDAO = (ProvReservaDAO)   context.getBean("ProvReservaDAO");
       ReservaDAO reservaDAO =  (ReservaDAO) context.getBean("ReservaDAO");
       ApartamentoDAO apartamentoDAO = (ApartamentoDAO) context.getBean("ApartamentoDAO");
       ElementoInventarioDAO elementoInventarioDAO = (ElementoInventarioDAO) context.getBean("elementoInventarioDAO");
       DetalleInventarioDAO detalleInventarioDAO = (DetalleInventarioDAO) context.getBean("detalleInventarioDAO");
       TareaDAO tareaDAO = (TareaDAO) context.getBean("TareaDAO");
       EstadoDAO estadoDAO = (EstadoDAO) context.getBean("EstadoDAO");

       TareasPorApartamentoDAO tareasPorApartamentoDAO = (TareasPorApartamentoDAO) context.getBean("TareasPorApartamentoDAO");




     Usuario juan = new Usuario();
        juan.setId(90);
        juan.setNombre("juan pablo");
        juan.setApellido("cuello");

        Usuario laura = new Usuario();
        laura.setId(80);
        laura.setNombre("Laura");
        laura.setApellido("ortiz");


        usuarioDAO.insert(juan);

        juan.setNombre("juan juan");


        usuarioDAO.insert(laura);

        System.out.println("");
        System.out.println(usuarioDAO.find(juan).toString());
        System.out.println("");

        usuarioDAO.update(juan);


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

        cliente.setUsuarioquerecibio(laura);

        clienteDAO.update(cliente);

        System.out.println("aca: ");
        System.out.println(clienteDAO.find(cliente));
        System.out.println("");



        ProvReserva provedorBooking = new ProvReserva();

        provedorBooking.setId(2);
        provedorBooking.setNombre("booking");
        provedorBooking.setDescripcion("booking por extranet");

        provReservaDAO.insert(provedorBooking);



        provedorBooking.setDescripcion("booking por sistema web");

        provReservaDAO.update(provedorBooking);

        System.out.println("");
        System.out.println(provReservaDAO.find(provedorBooking));
        System.out.println("");



        ProvReserva provedorSetur = new ProvReserva();

        provedorSetur.setId(3);
        provedorSetur.setNombre("Setur");
        provedorSetur.setDescripcion("Setur contacto directo ");

        provReservaDAO.insert(provedorSetur);



        Reserva reserva = new Reserva();

        reserva.setId(2);
        reserva.setCliente(cliente);
        reserva.setNum_personas(5);
        reserva.setTarifa_total(200.00);
        reserva.setComentario("comentario en la reserva");

        reserva.setProveedorreserva(provedorBooking);

        reservaDAO.insert(reserva);




        reserva.setProveedorreserva(provedorSetur);

        reservaDAO.update(reserva);


        System.out.println("");
        System.out.println(reservaDAO.find(reserva));
        System.out.println("");



        Apartamento apartamento1 = new Apartamento();

        apartamento1.setId(2);
        apartamento1.setNro_piso(2);
        apartamento1.setOcupacion_maxima(4);
        apartamento1.setNombre_edificio("La Porteña");
        apartamento1.setComentario("facil acceso directo desde la calle.");

        apartamentoDAO.insert(apartamento1);
        apartamento1.setComentario("facil acceso directo desde la calle y con baño privado");

        apartamentoDAO.update(apartamento1);


        System.out.println("");
        System.out.println(apartamentoDAO.find(apartamento1));
        System.out.println("");






        ElementoInventario pala = new ElementoInventario();
        pala.setId(1);
        pala.setNombre("pala ancha");
        pala.setCodigo("xaaw21");
        pala.setDescripcion("pala para pozos");
        pala.setFecha_insercion(Calendar.getInstance().getTime().toString());
        pala.setUsuarioquecompro(juan);

        elementoInventarioDAO.insert(pala);
        pala.setUsuarioquecompro(laura);
        elementoInventarioDAO.update(pala);



        System.out.println("");
        System.out.println(elementoInventarioDAO.find(pala));
        System.out.println("");







        DetalleInventario detalleInventario = new DetalleInventario();

        detalleInventario.setId(4);
        detalleInventario.setElementoinventario(pala);
        detalleInventario.setApartamento(apartamento1);
        detalleInventario.setCantidad(2);
        detalleInventario.setFecha_insercion(Calendar.getInstance().getTime().toString());
        detalleInventario.setRealizadopor(juan);


        detalleInventarioDAO.insert(detalleInventario);

        detalleInventario.setCantidad(1);

        detalleInventarioDAO.update(detalleInventario);


        System.out.println("");
        System.out.println(detalleInventarioDAO.find(detalleInventario));
        System.out.println("");




        Tarea limpiar = new Tarea();
        limpiar.setNombre("limpiar pieza");
        limpiar.setDescripcion("limpiar todo hasta armarios");
        limpiar.setId(2);


        tareaDAO.insert(limpiar);
        limpiar.setDescripcion("limpiar todo hasta armarios y los baños");
        tareaDAO.update(limpiar);



        System.out.println("");
        System.out.println(tareaDAO.find(limpiar));
        System.out.println("");



        Estado realizado = new Estado();
        realizado.setId(8);
        realizado.setNombre("realizado");
        realizado.setDescripcion("totalmente completo");

        estadoDAO.insert(realizado);

        realizado.setDescripcion("totalmente completo y registrado en sistema");
        estadoDAO.update(realizado);


        System.out.println("");
        System.out.println(estadoDAO.find(realizado));
        System.out.println("");



     TareasPorApartamento limpiarPieza1 = new TareasPorApartamento();
             limpiarPieza1.setId(4);
             limpiarPieza1.setTarea(limpiar);
             limpiarPieza1.setApartamento(apartamento1);
             limpiarPieza1.setEstado(realizado);
             limpiarPieza1.setRealizarpor(juan);
             limpiarPieza1.setDescripcion("debe realizarse antes de las 12 am");


             tareasPorApartamentoDAO.insert(limpiarPieza1);
             limpiarPieza1.setDescripcion("debe realizarse antes de las 13 hs");

            tareasPorApartamentoDAO.update(limpiarPieza1);


        System.out.println("");
        System.out.println(tareasPorApartamentoDAO.find(limpiarPieza1));
        System.out.println("");


     tareasPorApartamentoDAO.remove(limpiarPieza1);


        usuarioDAO.remove(juan);
        usuarioDAO.remove(laura);

        clienteDAO.remove(cliente);

        provReservaDAO.remove(provedorBooking);
        provReservaDAO.remove(provedorSetur);

        reservaDAO.remove(reserva);


        estadoDAO.remove(realizado);

        apartamentoDAO.remove(apartamento1);
        elementoInventarioDAO.remove(pala);
        tareaDAO.remove(limpiar);
        detalleInventarioDAO.remove(detalleInventario);

    }







}