package com.cuellojuan.tasks;


import com.cuellojuan.dao.ReporteInventarioDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static com.cuellojuan.App.context;


public class BuscarPiezasSucias extends Thread{


//    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    ReporteInventarioDAO reporteInventarioDAO = (ReporteInventarioDAO) context.getBean("reporteInventarioDAO");



    public BuscarPiezasSucias() {
    }



    public void run()
        {

            try {
                System.out.println("piezas por limpiar: "+ reporteInventarioDAO.obtenerCantidadDePiezasPorLimpiar());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Este proceso de buscar piezas sucias a terminado");
        }



        }









