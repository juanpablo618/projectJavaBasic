package com.cuellojuan.tasks;


import com.cuellojuan.dao.ReporteInventarioDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static com.cuellojuan.App.context;


public class BuscarPiezasConVentiladores extends Thread{



    ReporteInventarioDAO reporteInventarioDAO = (ReporteInventarioDAO) context.getBean("reporteInventarioDAO");



    public BuscarPiezasConVentiladores() {
    }



    public void run()
        {

            try {
                System.out.println("piezas que tienen ventiladores: ".concat(reporteInventarioDAO.obtenerPiezasConVentiladores().toString()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("El proceso de obtener piezas con ventiladores ha terminado");
        }



        }









