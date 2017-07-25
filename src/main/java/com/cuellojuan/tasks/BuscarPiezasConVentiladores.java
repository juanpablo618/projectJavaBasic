package com.cuellojuan.tasks;


import com.cuellojuan.dao.ReporteInventarioDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;


public class BuscarPiezasConVentiladores extends Thread{


    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    ReporteInventarioDAO reporteInventarioDAO = (ReporteInventarioDAO) context.getBean("reporteInventarioDAO");



    public BuscarPiezasConVentiladores() {
    }



    public void run()
        {

            try {
                System.out.println("piezas que tienen ventiladores: ");
                System.out.println(reporteInventarioDAO.obtenerPiezasConVentiladores().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("El proceso de obtener piezas con ventiladores ha terminado");
        }



        }









