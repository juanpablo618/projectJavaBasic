package com.cuellojuan.tasks;


import com.cuellojuan.dao.ClientesPorBookingDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static com.cuellojuan.App.context;

public class ReservasPorBooking extends Thread{


    ClientesPorBookingDAO clientesPorBookingDAO = (ClientesPorBookingDAO) context.getBean("clientesPorBookingDAO");


    public ReservasPorBooking() {
    }


    public void run()
    {

        try {
            System.out.println("Reservas hechas Por Booking".concat(clientesPorBookingDAO.obtenerCantidadDeClientesQueVienenPorBooking().toString()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Este proceso de buscar reservas hechas por booking a terminado");
    }




}
