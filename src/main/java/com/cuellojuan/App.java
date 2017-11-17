package com.cuellojuan;

import com.cuellojuan.dao.*;
import com.cuellojuan.dao.ApartamentoDAO;
import com.cuellojuan.entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


public class App {

    public static final ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {


        ClienteDAO clienteDAO = (ClienteDAO) context.getBean("ClienteDAO");
        UsuarioDAO usuarioDAO = (UsuarioDAO)   context.getBean("UsuarioDAO");
        ProvReservaDAO provReservaDAO = (ProvReservaDAO)   context.getBean("ProvReservaDAO");
        ReservaDAO reservaDAO =  (ReservaDAO) context.getBean("ReservaDAO");
        ApartamentoDAO apartamentoDAO = (ApartamentoDAO) context.getBean("ApartamentoDAO");
        ElementoInventarioDAO elementoInventarioDAO = (ElementoInventarioDAO) context.getBean("elementoInventarioDAO");
        TareaDAO tareaDAO = (TareaDAO) context.getBean("TareaDAO");
        EstadoDAO estadoDAO = (EstadoDAO) context.getBean("EstadoDAO");
        ServicioDAO  servicioDAO = (ServicioDAO) context.getBean("ServicioDAO");


        Estado realizado = new Estado();
        realizado.setDescripcion("realizado ya completado");
        realizado.setNombre("realizado");
        estadoDAO.insert(realizado);

        Estado vendido = new Estado();
        vendido.setNombre("vendido");
        vendido.setDescripcion("vendido ya facturado");
        estadoDAO.insert(vendido);


        Apartamento apartamento1 = new Apartamento();

        apartamento1.setComentario("excelente vista al rio");
        apartamento1.setNombre_edificio("La porteña hab. 1");
        apartamento1.setNro_piso(1);
        apartamento1.setOcupacion_maxima(2);


        Apartamento apartamento2 = new Apartamento();

        apartamento2.setComentario("excelente vista al rio depto 2");
        apartamento2.setNombre_edificio("La porteña hab. 2");
        apartamento2.setNro_piso(1);
        apartamento2.setOcupacion_maxima(4);


        Apartamento apartamento3 = new Apartamento();

        apartamento3.setComentario("acceso directo desde la calle apartamento 3");
        apartamento3.setNombre_edificio("La porteña hab. 3");
        apartamento3.setNro_piso(1);
        apartamento3.setOcupacion_maxima(2);

        Usuario juanPablo = new Usuario();
        juanPablo.setApellido("Cuello");
        juanPablo.setNombre("Juan Pablo");

        usuarioDAO.insert(juanPablo);


        Tarea limpiar = new Tarea();
        limpiar.setDescripcion("limpiar toda la habitación");
        //limpiar.setRealizador(juanPablo);
        limpiar.setNombre("limpiar");
        limpiar.setEstado(realizado);
        tareaDAO.insert(limpiar);

        Tarea arregloDeLuzPrincipal = new Tarea();
        arregloDeLuzPrincipal.setDescripcion("Arreglar luz principal de la habitación");
        //arregloDeLuzPrincipal.setRealizador(juanPablo);
        arregloDeLuzPrincipal.setNombre("arreglar Luz principal");
        arregloDeLuzPrincipal.setEstado(realizado);
        tareaDAO.insert(arregloDeLuzPrincipal);


        List<Tarea> tareasARealizarEnPieza1 = new ArrayList<>();
        tareasARealizarEnPieza1.add(arregloDeLuzPrincipal);
        tareasARealizarEnPieza1.add(limpiar);

        apartamento1.setTareasARealizarle(tareasARealizarEnPieza1);

        ElementoInventario lamparaDePie = new ElementoInventario();
        lamparaDePie.setNombre("Lampara de pie");
        lamparaDePie.setFecha_insercion("2017-09-02");
        lamparaDePie.setCodigo("Lamp001");
        lamparaDePie.setDescripcion("Lampara de pie antigua valor de rotura 4000 pesos");
        lamparaDePie.setComprador(juanPablo);
        elementoInventarioDAO.insert(lamparaDePie);


        ElementoInventario caloventor = new ElementoInventario();
        caloventor.setNombre("Caloventor");
        caloventor.setFecha_insercion("2017-09-20");
        caloventor.setCodigo("Caloventor001");
        caloventor.setDescripcion("Caloventor unitario para pieza individual");
        caloventor.setComprador(juanPablo);
        elementoInventarioDAO.insert(caloventor);


        ElementoInventario camaDoble = new ElementoInventario();
        camaDoble.setNombre("cama Doble");
        camaDoble.setFecha_insercion("2017-09-20");
        camaDoble.setCodigo("camaDoble001");
        camaDoble.setDescripcion("cama doble con colchnon");
        camaDoble.setComprador(juanPablo);
        elementoInventarioDAO.insert(camaDoble);


        Servicio ventaBebidaAlcoholica = new Servicio();
        ventaBebidaAlcoholica.setDescripcion("venta de medida de whisky a la habitacion.");
        ventaBebidaAlcoholica.setNombre("venta de bebida alcoholica");
        ventaBebidaAlcoholica.setCodigo("bebidaAlcoholica01");
        ventaBebidaAlcoholica.setEstado(vendido);
        ventaBebidaAlcoholica.setVendedor(juanPablo);
        servicioDAO.insert(ventaBebidaAlcoholica);


        Servicio limpiezaAdicional = new Servicio();
        limpiezaAdicional.setVendedor(juanPablo);
        limpiezaAdicional.setDescripcion("limpieza adicional de habitacion por pedido del cliente");
        limpiezaAdicional.setNombre("limpieza adicional");
        limpiezaAdicional.setEstado(realizado);
        servicioDAO.insert(limpiezaAdicional);

        List<Servicio> serviciosBrindadosPieza1 = new ArrayList<>();
        serviciosBrindadosPieza1.add(ventaBebidaAlcoholica);
        serviciosBrindadosPieza1.add(limpiezaAdicional);


        List<ElementoInventario> inventarioApartamento1= new ArrayList<>();

        inventarioApartamento1.add(lamparaDePie);
        inventarioApartamento1.add(caloventor);
        inventarioApartamento1.add(camaDoble);


        apartamento1.setElementosDeInventarioQuePosee(inventarioApartamento1);
        apartamento1.setTareasARealizarle(tareasARealizarEnPieza1);
        apartamento1.setServiciosBrindados(serviciosBrindadosPieza1);

        apartamentoDAO.insert(apartamento1);
        apartamentoDAO.insert(apartamento2);
        apartamentoDAO.insert(apartamento3);


        Cliente cliente1 = new Cliente();

        cliente1.setApellido("apellido cliente");
        cliente1.setNombre("nombre cliente");
        cliente1.setFecha_nacimiento("2017-09-09");
        cliente1.setTel_fijo("31231");
        cliente1.setTel_celular("3311311313");
        cliente1.setEmail("email@delCLiente.com");
        cliente1.setComentario("comentario sobre el cliente");
        cliente1.setReceptor(juanPablo);
        cliente1.setFecha_insercion("2017-05-02");


        clienteDAO.insert(cliente1);

        ProvReserva proveedorDeLaReservaBooking = new ProvReserva();
        proveedorDeLaReservaBooking.setDescripcion("booking.com entrar por extranet");
        proveedorDeLaReservaBooking.setNombre("Booking");
        provReservaDAO.insert(proveedorDeLaReservaBooking);


        Reserva reserva1 = new Reserva();
        reserva1.setCliente(cliente1);
        reserva1.setNum_personas(4);
        reserva1.setTarifa_total(60.00);
        reserva1.setComentario("comentario de la reserva");
        reserva1.setProveedor(proveedorDeLaReservaBooking);


        List<Apartamento> listadeHabParaReserva1 = new ArrayList<>();
        listadeHabParaReserva1.add(apartamento1);
        listadeHabParaReserva1.add(apartamento3);

        reserva1.setApartPorReserva(listadeHabParaReserva1);

        reservaDAO.insert(reserva1);

        System.out.println("");
        System.out.println("");
        System.out.println("Find de usuario: "+ usuarioDAO.find(juanPablo).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Apartamento1: "+ apartamentoDAO.find(apartamento1).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Reserva: "+ reservaDAO.find(reserva1).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de cliente: "+ clienteDAO.find(cliente1).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de estado realizado: "+ estadoDAO.find(realizado).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de estado vendido: "+ estadoDAO.find(vendido).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Elemento de Inventario: "+ elementoInventarioDAO.find(caloventor).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Elemento de Inventario: "+ elementoInventarioDAO.find(camaDoble).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Elemento de Inventario: "+ elementoInventarioDAO.find(caloventor).toString());

        System.out.println("");
        System.out.println("");
        System.out.println("Find de Proveedor de reserva: "+ provReservaDAO.find(proveedorDeLaReservaBooking).toString());


        System.out.println("");
        System.out.println("");
        juanPablo.setApellido("rosales");
        usuarioDAO.update(juanPablo);
        System.out.println("");
        System.out.println("");
        System.out.println("Find de usuario modificado: "+ usuarioDAO.find(juanPablo).toString());
        System.out.println("");
        System.out.println("");

        apartamentoDAO.remove(apartamento1);
        apartamentoDAO.remove(apartamento2);
        apartamentoDAO.remove(apartamento3);

        reservaDAO.remove(reserva1);

        clienteDAO.remove(cliente1);

        elementoInventarioDAO.remove(caloventor);
        elementoInventarioDAO.remove(lamparaDePie);
        elementoInventarioDAO.remove(camaDoble);

        estadoDAO.remove(vendido);
        estadoDAO.remove(realizado);

        provReservaDAO.remove(proveedorDeLaReservaBooking);

        servicioDAO.remove(limpiezaAdicional);
        servicioDAO.remove(ventaBebidaAlcoholica);

        tareaDAO.remove(arregloDeLuzPrincipal);
        tareaDAO.remove(limpiar);

        usuarioDAO.remove(juanPablo);


    }
}