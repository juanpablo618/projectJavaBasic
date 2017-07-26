package com.cuellojuan.dao;

import com.cuellojuan.dao.impl.GenericDAOImplHotel;
import com.cuellojuan.entity.ClientesPorBooking;

import java.sql.SQLException;
import java.util.List;

public class ClientesPorBookingDAO extends GenericDAOImplHotel<ClientesPorBooking> {

    public ClientesPorBookingDAO() {
    }


    public List<ClientesPorBooking> obtenerCantidadDeClientesQueVienenPorBooking() throws SQLException {
        return super.obtenerCantidadDeClientesQueVienenPorBooking();
    }




}

