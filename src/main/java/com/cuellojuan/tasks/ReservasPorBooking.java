package com.cuellojuan.tasks;


import com.cuellojuan.dao.ReservasPorBookingDAO;

import java.sql.SQLException;

import static com.cuellojuan.App.context;

public class ReservasPorBooking extends Thread{


    ReservasPorBookingDAO reservasPorBookingDAO = (ReservasPorBookingDAO) context.getBean("reservasPorBookingDAO");


    public ReservasPorBooking() {
    }


    public void run()
    {

        try {
            System.out.println("Reservas hechas Por Booking".concat(reservasPorBookingDAO.obtenerCantidadDeClientesQueVienenPorBooking().toString()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Este proceso de buscar reservas hechas por booking a terminado");
    }




}
