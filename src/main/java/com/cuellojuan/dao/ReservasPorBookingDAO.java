package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImplHotel;
import com.cuellojuan.entity.ReservasPorBooking;

import java.sql.SQLException;
import java.util.List;

public class ReservasPorBookingDAO extends GenericDAOImplHotel<ReservasPorBooking> {

    public ReservasPorBookingDAO() {
    }


    public List<ReservasPorBooking> obtenerCantidadDeClientesQueVienenPorBooking() throws SQLException {
        return super.obtenerCantidadDeClientesQueVienenPorBooking();
    }




}

