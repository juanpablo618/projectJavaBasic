package com.cuellojuan.tasks;


import com.cuellojuan.dao.ReporteInventarioDAO;
import com.cuellojuan.entity.Relinventario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class BuscarPiezasSucias extends Thread{


    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    ReporteInventarioDAO reporteInventarioDAO = (ReporteInventarioDAO) context.getBean("reporteInventarioDAO");



    public BuscarPiezasSucias() {
    }



    public void run()
        {

            try {
                System.out.println("piezas por limpiar");
                System.out.println(reporteInventarioDAO.obtenerCantidadDePiezasPorLimpiar());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Este proceso de buscar piezas sucias a terminado");
        }



        }









